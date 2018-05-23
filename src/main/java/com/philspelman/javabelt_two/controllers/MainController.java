package com.philspelman.javabelt_two.controllers;

import com.philspelman.javabelt_two.models.Idea;
import com.philspelman.javabelt_two.models.Likevote;
import com.philspelman.javabelt_two.models.User;
import com.philspelman.javabelt_two.services.IdeaService;
import com.philspelman.javabelt_two.services.UserService;
import com.philspelman.javabelt_two.validator.UserValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,WebMvcAutoConfiguration.class })
@Controller
public class MainController {
    //Model: ThingModel, object: thingModel
    // hookup the thingModel service
//    private ThingModelService thingModelService;
    private UserService userService;
    private UserValidator userValidator;
    private IdeaService ideaService;

    public MainController(UserService userService, UserValidator userValidator, IdeaService ideaService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ideaService = ideaService;
    }


    @GetMapping("/main")
    public String login(@ModelAttribute User user, BindingResult bindingResult,
                        @ModelAttribute("status") String status,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("status", "Invalid credentials. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("status", "Successfully logged out.");
        }
        return "registrationPage";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user,
                               Model model) {

        return "registrationPage";
    }


    private static void iterateErrorResults(List<ObjectError> obj) {
        for (Object object : obj) {
            if (object instanceof Error) {
                Error err = (Error) object;
                System.out.println(err.toString());
            }
            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                System.out.println(objectError.getCode());
            }
        }
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session) {

        System.out.println("got POST request for new user registration");
        userValidator.validate(user, bindingResult);
        iterateErrorResults(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "registrationPage";
        }
        userService.saveWithUserRole(user);
        return "redirect:/login";
    }


    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model,
                        @ModelAttribute("user") User user) {

        System.out.println("reached the login request mapping");
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again");
            return "loginPage";
        }
        if (logout != null) {
            //meaning they ARE logging out
            model.addAttribute("logoutMessage", "Successfully logged out");
            return "registrationPage";

        }
        return "loginPage";
    }



    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {

        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        model.addAttribute("heading_message", "TV Shows");

        //get all ideas
        List<Idea> ideas = ideaService.allIdeas();
        model.addAttribute("ideas", ideas);

        return "homePage";
    }


    //route for GET request to EDIT the details of a Idea
    @GetMapping("/ideas/view/{id}")
    public String viewSelectedIdea(@PathVariable Long id,
                                   Principal principal,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));

        List<Likevote> likevotesList = ideaService.getLikeVotesForSelectedIdea(id);

        model.addAttribute("likevotesList", likevotesList);


        model.addAttribute("idea", ideaService.findIdeaById(id));
        return "viewSelectedIdea";
    }



    //route for GET request to add a new Idea
    @GetMapping("/ideas/new")
    public String newIdea(@ModelAttribute("idea") Idea idea,
                          Model model,
                          Principal principal) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "formNewIdea";

    }


    //route for POST request to add a new Idea



    @PostMapping("/ideas/new")
    public String newIdea(@Valid @ModelAttribute("idea") Idea idea,
                          BindingResult bindingResult,
                          Model model,
                          HttpSession session) {

        //show validator

        if (bindingResult.hasErrors()) {
            return "formNewIdea";
        } else {
            System.out.println("entered valid new show...saving to db");
            ideaService.addIdea(idea);
            return "redirect:/";

        }

        //otherwise the data were valid
    }
//    @PostMapping("/ideas/new")
//    public String addNewIdea(@Valid @ModelAttribute("idea") Idea idea,
//                          Principal principal,
//                          BindingResult bindingResult,
//                          Model model) {
//
//        System.out.println("Recieved POST request for new idea");
//
//        String username = principal.getName();
//        model.addAttribute("currentUser", userService.findByUsername(username));
//        idea.setAddedBy(userService.findByUsername(username));
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("idea", idea);
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            return "formNewIdea";
//        }
//
//        this.ideaService.addIdea(idea);
//        return "redirect:/";
//    }


    //route for GET request to EDIT the details of a Idea
//    @GetMapping("/ideas/edit/{id}")
    @GetMapping("/ideas/edit/{id}")
    public String editSelectedIdea(@PathVariable Long id,
                                   Principal principal,
                                   Model model) {
        System.out.println("GET edit request");
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));

        model.addAttribute("idea", ideaService.findIdeaById(id));
        return "formEditIdea";
    }






    @PostMapping("/ideas/edit")
    public String editIdea(@Valid @ModelAttribute("idea") Idea idea,
                           BindingResult bindingResult,
                           Model model,
                           HttpSession session,
                           Principal principal) {

        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));

        System.out.println("got POST request for EDIT idea ");
//        userValidator.validate(user, bindingResult);
        iterateErrorResults(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            model.addAttribute("idea", idea);

            return "formEditIdea";
        }
            ideaService.updateIdea(idea);
        return "redirect:/";
    }
//
//    //route for POST request to EDIT the details of a Idea
//    @PostMapping("/ideas/edit")
//    public String editSelectedIdea(@Valid @ModelAttribute("idea") Idea idea,
////                                   @PathVariable Long id,
////                                   Principal principal,
////                                   RedirectAttributes redirectAttributes,
//                                   HttpSession httpSession,
//                                   Model model,
//                                   BindingResult bindingResult) {
////        String username = principal.getName();
////        model.addAttribute("currentUser", userService.findByUsername(username));
//        System.out.println("trying to edit idea!");
//
//        //fixme: may need to re-add the idea
//
//        if (bindingResult.hasErrors()) {
//            System.out.println("errors in the data");
////            model.addAttribute("idea", idea);
//            return "formEditIdea";
//        } else {
//            System.out.println("idea stuff: " + idea.toString());
//            ideaService.updateIdea(idea);
//            return "redirect:/";
//        }
//
////        if (!ideaService.updateIdea(idea)) {
////            model.addAttribute("idea", idea);
////            model.addAttribute("unique", "already in database");
////            return "formEditIdea";
////        }
//    }


    //route for POST request to add rating, stars, etc. associated with a Idea
    @GetMapping("/ideas/likevote/{id}")
    public String toggleIdeaLikeByUser(@PathVariable Long id,
//                                         @RequestParam int id,
                                         Principal principal,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {
        //logic to see if the thing can be added
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));

        Long userId = userService.findByUsername(username).getId();

        System.out.println("Trying to toggle the like for this user " + username);

        ideaService.toggleLikevoteByUsername(id, username);

//        if (val < 1 || val > 5) {
//            redirectAttributes.addFlashAttribute("invalid", "Invalid rating");
//            return String.format(("redirect:/ideas/%d"), id);
//        }
//        //try to add a rating, it'll return TRUE if it worked
//        if (ideaService.rateIdea(val, id, principal.getName())) {
//            return "redirect:/";
//        }

        return String.format("redirect:/ideas/view/%d", id);
    }
















//    //route for POST request to add rating, stars, etc. associated with a Idea
//    @PostMapping("/ideas/{id}/rating")
//    public String associateThingWithIdea(@PathVariable Long id,
//                                         @RequestParam int val,
//                                         Principal principal,
//                                         RedirectAttributes redirectAttributes
//    ) {
//        //logic to see if the thing can be added
//        if (val < 1 || val > 5) {
//            redirectAttributes.addFlashAttribute("invalid", "Invalid rating");
//            return String.format(("redirect:/ideas/%d"), id);
//        }
//        //try to add a rating, it'll return TRUE if it worked
//        if (ideaService.rateIdea(val, id, principal.getName())) {
//            return "redirect:/";
//        }
//
//        redirectAttributes.addFlashAttribute("invalid", "Can't do double ratings");
//        return String.format(("redirect:/ideas/%d"), id);
//    }
//

    @RequestMapping("/ideas/delete/{id}")
    public String deleteIdea(@PathVariable("id") Long id) {
        ideaService.deleteIdeaById(id);
        return "redirect:/";
    }

}