package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;
import it.polito.applied.mad.teamMaker.pojo.User;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mailAddress.from}")
	private String from;
	
	@Value("${baseUrlDomain}")
	private String baseUrlDomain;
	
	private final String STUDENT_EMAIL_DOMAIN = "@studenti.polito.it";

	@Override
	public void sendConfirmationMessage(String studentId, String token, List<User> users, String groupName, String cancelingToken) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		User actual=null;
		for(User u : users){
			if(u.getStudentId().equals(studentId)){
				actual = u;
				break;
			}
		}
		
		if(actual!=null){
			String email = getEmailFromStudentId(actual.getStudentId());
			if(email!=null){
				String text="Hi "+actual.getFirstname()+" "+actual.getLastname()+"\n\n"
						+ "concerning Mobile Application Development class, you have been added to a team";
				if(!groupName.isEmpty())
					text+=" ("+groupName+")";
				text+=".\nGroup attendees are:\n";
				for(User u : users)
					text+=" - "+u.getStudentId()+", "+u.getFirstname()+" "+u.getLastname()+"\n";
				text+="\nIf you want to confirm your partecipation to this team, please click the link below.\n\n";
				text+=""+baseUrlDomain+"/confirm?studentId="+studentId+"&token="+token+"\n\n\n\n";
				text+="Otherwise, if you do not want accept this invitation and you want cancel it, click on the follow link.\n\n";
				text+=""+baseUrlDomain+"/cancel?studentId="+studentId+"&token="+cancelingToken+"\n\n";
				text+="Regards\nProf. Giovanni Malnati";
				message.setFrom(from);
				message.setTo(email);
				message.setSubject("MAD 2016 - Request for team creation");
				message.setText(text);
				mailSender.send(message);
			}
		}
		
	}

	@Override
	public void sendCancelingMessage(String s, GroupRequest groupRequest) {
		SimpleMailMessage message = new SimpleMailMessage();
		String email = getEmailFromStudentId(s);
		if(email!=null){
			String text="Dear student,\n\nrequest regarding team "+groupRequest.getGroupName()+" has been canceled by one of invited.\n"
					+ "So now you are in no one team. Please consider to redo team request on course website.\n\n"+baseUrlDomain+"\n\n"
					+ "Regards\nProf. Giovanni Malnati";
					
			message.setFrom(from);
			message.setTo(email);
			message.setSubject("MAD 2016 - Request canceled");
			message.setText(text);
			mailSender.send(message);
		}
	}
	
	
	private String getEmailFromStudentId(String studentId) {
		if(studentId.length()==6){
			String digitRegex="\\b\\d{6}\\b";
			if(studentId.matches(digitRegex)){
				return "s"+studentId+STUDENT_EMAIL_DOMAIN;
			}
		}else if(studentId.length()==7){
			String sDigitRegex="\\bs\\d{6}\\b";
			if(studentId.matches(sDigitRegex)){
				return studentId+STUDENT_EMAIL_DOMAIN;
			}
		}
		
		return null;
	}
	

}
