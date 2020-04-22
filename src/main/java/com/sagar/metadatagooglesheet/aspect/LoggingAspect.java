package com.sagar.metadatagooglesheet.aspect;

import com.sagar.metadatagooglesheet.dto.AddDeveloperRequest;
import com.sagar.metadatagooglesheet.dto.AddNoteRequest;
import com.sagar.metadatagooglesheet.model.Developer;
import com.sagar.metadatagooglesheet.model.Note;
import com.sagar.metadatagooglesheet.model.Notification;
import com.sagar.metadatagooglesheet.model.Ticket;
import com.sagar.metadatagooglesheet.repository.DeveloperRepository;
import com.sagar.metadatagooglesheet.repository.NotificationRepository;
import com.sagar.metadatagooglesheet.repository.TicketRepository;
import com.sagar.metadatagooglesheet.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Around("execution(* com.sagar.metadatagooglesheet.controller.AuthController.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        LOGGER.info("Execution time of " + className + "." + methodName + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }

    @After("execution(* com.sagar.metadatagooglesheet.controller.TicketController.addDeveloper(com.sagar.metadatagooglesheet.dto.AddDeveloperRequest))")
    public void getTicketCall(JoinPoint joinPoint) throws Throwable {
        Object[] addDeveloperRequest = joinPoint.getArgs().clone();
        log.info(String.valueOf(addDeveloperRequest[0]));
        AddDeveloperRequest addDeveloperRequest1 = (AddDeveloperRequest) addDeveloperRequest[0];
        long devId = addDeveloperRequest1.getDeveloperId();
        long ticketId = addDeveloperRequest1.getTicketId();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedInUser = principal.getUsername();
        String devName = developerRepository.findById(devId).get().getName();
        String ticketTitle = ticketRepository.findById(ticketId).get().getTitle();
        String message = " You were added to the ticket: " + ticketTitle + " by " + loggedInUser;
        Notification notification = notificationRepository.save(new Notification(message, false));
        Developer developer = developerRepository.findById(devId).get();
        developer.getNotifications().add(notification);
        com.sagar.metadatagooglesheet.model.User user = userRepository.findByUsername(devName).get();
        user.getNotifications().add(notification);
        userRepository.save(user);
        developerRepository.save(developer);
    }

    @After("execution(* com.sagar.metadatagooglesheet.controller.TicketController.addNote(com.sagar.metadatagooglesheet.dto.AddNoteRequest))")
    public void getMentions(JoinPoint joinPoint) throws Throwable {
        Object[] addDeveloperRequest = joinPoint.getArgs().clone();
        AddNoteRequest note = (AddNoteRequest) addDeveloperRequest[0];
        String noteText = note.getText();
        String createdBy = note.getCreatedBy();
        String[] words = noteText.split(" ");
        List<String> mentions = new ArrayList<>();
        for(int i = 0; i< words.length; i++) {
            if (words[i].startsWith("@")) {
                mentions.add(words[i]);
                Optional<com.sagar.metadatagooglesheet.model.User> userObj = userRepository.findByUsername(words[i].replaceAll("[^\\p{L}\\p{Z}]",""));
                if(userObj.isPresent()){
                    com.sagar.metadatagooglesheet.model.User user = userObj.get();
                    String loggedInUser = createdBy;
                    String message = " You were mentioned in notes chat by " + loggedInUser;
                    Notification notification = notificationRepository.save(new Notification(message, false));
                    user.getNotifications().add(notification);
                    userRepository.save(user);
                }


            }
        }





    }

}
