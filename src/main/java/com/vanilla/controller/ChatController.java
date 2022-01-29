package com.vanilla.controller;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanilla.domain.ChatMessage;
import com.vanilla.domain.CreatePost;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;
import com.vanilla.events.ActiveEvent;
import com.vanilla.events.ParticipantRepository;
import com.vanilla.repository.MessageRepository;
import com.vanilla.repository.UserRepository;
import com.vanilla.service.PostService;
import com.vanilla.service.UserProfileService;
import com.vanilla.serviceimpl.MessageServiceImpl;
import com.vanilla.serviceimpl.UserServiceImpl;

/*
*@Author
*Sylvester Kalumbi
*/

@Controller
public class ChatController {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	private SimpMessagingTemplate messageTemplate;
	@Autowired
	private MessageServiceImpl messageServiceImpl;

	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private PostService postservice;
	@Autowired
	private UserRepository userRepository;
	// private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@MessageMapping("/chat.private.{username}")
	public ChatMessage message(@Payload ChatMessage message, @DestinationVariable("username") String username,
			Principal principal) {
		String fromUsername = principal.getName();
		System.out.println(message.toString());
		message.setUsername(principal.getName());
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formart = DateTimeFormatter.ofPattern(" HH:mm a dd-MM-yyyy");
		String dateTimeFormat = dateTime.format(formart);
		message.setLocalDatetime(dateTimeFormat);
        
		/*
		 * Getting the data of the user sending the message
		 */
		
		User fromUser = userServiceImpl.getUser(fromUsername);
		/*
		 * Data of the person reciving the message
		 */
		User toUser = userServiceImpl.findByUsername(username);
		toUser.getFirstname();
		toUser.getLastname();
		toUser.getUsername();
		

		messageServiceImpl.saveMessage(fromUser, toUser, message);

		// my complete implementation

		messageTemplate.convertAndSend("/user/" + username + "/top/", message);

		return message;
	}

	@SubscribeMapping("/active.participants")
	public Collection<ActiveEvent> activeUsers() {

		System.out.println("current " + participantRepository.getActiveSessions().values().toString());
		return participantRepository.getActiveSessions().values();

	}

	@RequestMapping(value = "/conversation/{username}", produces = "application/json")
	@ResponseBody
	public List<ChatMessage> fetchConversation(@PathVariable("username") String username, Principal principal) {
		System.out.println("another user " + username);
		String thisUser = principal.getName();
		User me = userServiceImpl.findByUsername(thisUser);
	
		User toUser = userServiceImpl.findByUsername(username);
		System.out.println(me.getUsername() + " fetching conversation with " + toUser.getFirstname());
		
	    	List<ChatMessage> messages = messageServiceImpl.Conversation(me.getId(), toUser.getId());
		//List<ChatMessage> messages = messageServiceImpl.fetchConversation(me.getId(), toUser.getId());
		
		System.out.println(messages + "fetched messages");

		return messages;

	}

	@RequestMapping(value = "/conversations", produces = "application/json")
	@ResponseBody
	public List<ChatMessage> myMessages(Principal principal) {

		String thisUser = principal.getName();
		User user = userServiceImpl.findByUsername(thisUser);
		List<ChatMessage> messages = messageServiceImpl.fetchMessages(user.getId());

		return messages;
	}

	

	/*
	 * this method was helping me to query users profile ewquest based on the on
	 * string{username} but not convinient many users coulhave the same so i
	 * changedt o use the profileid Instead
	 */
	/*
	 * @RequestMapping("/userprofile/{username}")
	 * 
	 * @ResponseBody public UserProfile profile(@PathVariable("username") String
	 * userProfile) {
	 * 
	 * System.out.println("userprofile request:: " + userProfile); UserProfile
	 * profile = userProfileService.findUserProfile(userProfile);
	 * 
	 * return profile; }
	 */
	/*
	 * insted am using the method below which is querying the profileId of the
	 * particular user
	 */
	@RequestMapping("/userprofile/{profileId}")
	@ResponseBody
	public UserProfile profile(@PathVariable("profileId") Long userProfile) {
		UserProfile profile = userProfileService.findUserProfile(userProfile);
		return profile;
	}

	/*
	 * @RequestMapping(value = "/upload", method = { RequestMethod.GET,
	 * RequestMethod.POST }) //@MessageMapping("/post.public.")
	 * 
	 * @ResponseBody public CreatePost post(@RequestBody CreatePost post, Principal
	 * principal) { //User user =
	 * userServiceImpl.findByUsername(principal.getName());
	 * userProfileService.saveProfile(post.getPhoto());
	 * 
	 * //UserProfile profile = new UserProfile(user); // photo = post.getPhoto();
	 * //System.out.println(photo + ":::::::");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // UserProfile profile= // CreatePost post=new CreatePost(post);
	 * 
	 * // System.out.println(profile.getUsername() + "has just posted something" +
	 * // profile + ":::"); // System.out.println(post.getPayload() + " the payload"
	 * + " photo " + photo); System.out.println(post.getPayload() +
	 * ":::::::::::::"+post); return post;
	 * 
	 * }
	 */
	// @RequestMapping(value = "/upload", method = { RequestMethod.GET,
	// RequestMethod.POST })
	@MessageMapping("/post.update.profile.")
	public void UpdateProfilePic(@Payload CreatePost post, Principal principal) {
		System.out.println(post.getPayload() + ":::::::::::::" + post.toString());
		User user = userServiceImpl.findByUsername(principal.getName());
		/*
		 * Transfer only an object to be persisted to the repository otherwise it will
		 * throw a proxyException
		 */
		UserProfile profile = userProfileService.findByUserProfile(user);
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formart = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a");
		String dateTimeFormat = dateTime.format(formart);
		post.setUsername(profile.getUsername());
		post.setFirstname(profile.getFirstname());
		post.setLastname(profile.getLastname());
		post.setId(profile.getId());
		// UserProfile profile=userProfileService.findByUserId(user.getId());
		post.setDateTime(dateTimeFormat);
		profile.setProfilepic(post.getPhoto());
		post.setProfileId(profile.getId());

		userProfileService.saveProfile(profile,user);

		messageTemplate.convertAndSend("/newsfeed/profile.", post);

	}

	@MessageMapping("/post.public.")
	public CreatePost post(@Payload CreatePost post, Principal principal ) {
		
		User user = userServiceImpl.findByUsername(principal.getName());
		UserProfile profile = userProfileService.findByUserProfile(user);
		String pic=profile.getProfilepic();
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formart = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm a");
		String dateTimeFormat = dateTime.format(formart);
		post.setUsername(profile.getUsername());
		post.setFirstname(profile.getFirstname());
		post.setLastname(profile.getLastname());
		post.setId(profile.getId());
		post.setSteamed("Steamed something");
		post.setDateTime(dateTimeFormat);
		post.setProfilePic(profile.getProfilepic()); 
		post.setProfileId(profile.getId());
		//System.out.println();
		postservice.savePost(profile, post);
		messageTemplate.convertAndSend("/newsfeed/post.", post);
		return post;
	}

}
