package estm.dsic.controllers;

import com.example.docapp.util.DateFormatter;
import estm.dsic.models.Note;
import estm.dsic.models.User;
import estm.dsic.services.NoteService;
import estm.dsic.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = "/")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    private final UserService userService;
    private NoteService noteService;

    public MainController() {
        super();
        // TODO Auto-generated constructor stub
        userService = new UserService();
        noteService = new NoteService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/auth")) {
            login(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/register")) {
            register(request, response);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/logout")) {
            logout(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/edit")) {
            showEdit(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/editUser")) {
            edit(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/delete")) {
            showDelete(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/deleteUser")) {
            deleteUser(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/note")){
            showNote(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/editNote")){
            editNote(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/addNote")){
            newNote(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/newNote")){
            showNewNote(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/delNote")){
            showNoteDelete(request, response, session);
        }
        if (request.getRequestURI().equals(getServletContext().getContextPath() + "/deleteNote")){
            deleteNote(request, response, session);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private void deleteNote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println("deleting...");
        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            boolean done = noteService.remove(Integer.valueOf(id));
            if (done) {
                session.setAttribute("message", "Success!");
                showHome(request, response,session);
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                session.setAttribute("message", "Erreur veuillez réessayer!");
                showHome(request, response,session);
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            session.setAttribute("message", "Erreur veuillez réessayer!");
            showHome(request, response,session);
            try {
                response.sendRedirect(request.getContextPath() + "/auth");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void showNewNote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
                    String id = request.getParameter("noteBtn");
                    System.out.println("ntdje" + id);
                    request.getRequestDispatcher("User/newNote.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showNoteDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            String id = request.getParameter("deleteBtn");
            System.out.println(id);
            if (id == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
            } else {
                Note note = noteService.getNote(id);
                System.out.println(note);
                if (note != null) {
                    request.setAttribute("currentNote", note);
                    request.getRequestDispatcher("User/delete.jsp").forward(request, response);
                }
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void newNote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer nb = (Integer) session.getAttribute("nbNotes");
        if (title != null && content != null) {
                String user = request.getParameter("email");
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                note.setEmail(user);
                note.setDate(LocalDateTime.now().format(DateFormatter.formatter));
                boolean a = noteService.add(note);
                if (a) {
                    nb++;
                    System.out.println("this is nb notes in new" + nb);
                    session.setAttribute("nbNotes", nb);
                    session.setAttribute("message", "Success!");
                    try {
                        showHome(request, response, session);
                        response.sendRedirect(request.getContextPath() + "/auth");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    session.setAttribute("message", "Error please try again.");
                    try {
                        showHome(request, response, session);
                        response.sendRedirect(request.getContextPath() + "/auth");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        } else {
            try {
                response.sendRedirect(request.getContextPath() + "/auth");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showNote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            String id = request.getParameter("editBtn");
            System.out.println(id);
            if (id == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
            } else {
                Note n = noteService.getNote(id);
                System.out.println(n);
                if (n!= null) {
                    request.setAttribute("currentNote", n);
                    request.getRequestDispatcher("User/edit.jsp").forward(request, response);
                }
            }


        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editNote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (request.getParameter("id") != null) {
            System.out.println("updating...");
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String date = LocalDateTime.now().format(DateFormatter.formatter);
            Note note = new Note();
            note.setId(id);
            note.setTitle(title);
            note.setContent(content);
            note.setDate(date);
            boolean done = noteService.update(note);
            if (done) {
                System.out.println("setting msg status");
                session.setAttribute("message", "Success!");
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                session.setAttribute("message", "Erreur veuillez réessayer!");
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            request.setAttribute("message", "Erreur veuillez réessayer!");
            showHome(request, response,session);
        }
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            String email = request.getParameter("deleteBtn");
            System.out.println(email);
            if (email == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
            } else {
                User u = userService.getUser(email);
                System.out.println(u);
                if (u != null) {
                    request.setAttribute("currentUser", u);
                    request.getRequestDispatcher("Admin/delete.jsp").forward(request, response);
                }
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (request.getParameter("email") != null) {
            String email = request.getParameter("email");
            User user = new User();
            user.setEmail(email);
            boolean done = userService.deleteUser(user);
            if (done) {
                request.setAttribute("message", "Success!");
                showHome(request, response,session);
            } else {
                request.setAttribute("message", "Erreur veuillez réessayer!");
                showHome(request, response,session);
            }
        }else{
            request.setAttribute("message", "Erreur veuillez réessayer!");
            showHome(request, response,session);
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            String email = request.getParameter("editBtn");
            System.out.println(email);
            if (email == null) {
                response.sendRedirect(request.getContextPath() + "/auth");
            } else {
                User u = userService.getUser(email);
                System.out.println(u);
                if (u != null) {
                    request.setAttribute("currentUser", u);
                    request.getRequestDispatcher("Admin/edit.jsp").forward(request, response);
                }
            }


        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void register(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        if (email != null) {
            String name = request.getParameter("name");
            String password = request.getParameter("pwd");
            String passwordconf = request.getParameter("pwdconf");
            String PASSWORD_PATTERN =
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
            String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Pattern passPattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = passPattern.matcher(password);
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher Ematcher = pattern.matcher(email);
            if (!passwordconf.equals(password)) {
                try {
                    request.setAttribute("message", "Passwords do not match.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!matcher.matches()) {
                try {
                    request.setAttribute("message", "Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, and one number");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!Ematcher.matches()) {
                request.setAttribute("message", "Please enter a valid email.");
                try {
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                User u = new User();
                u.setEmail(email);
                u.setName(name);
                u.setPwd(password);
                u.setAdmin(false);
                boolean a = userService.register(u);
                if (a) {
                    try {
                        request.setAttribute("message", "Succesfully registered, please login.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            try {
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Object logged = session.getAttribute("loggedIn");
        System.out.println("Logged in:" + logged);
        if (logged == null) {
            String email = request.getParameter("email");
            String password = request.getParameter("pwd");
            System.out.println("Passed " + email + " " + password);
            if(email==null || password==null){
                System.out.println("going to index");
                try {
                    request.setAttribute("message", "Please login.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                User user = new User();
                user.setEmail(email);
                user.setPwd(password);
                user = userService.Login(user);
                System.out.println(user);
                if( user != null ){
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("user", user);
                    showHome(request,response,session);
                }else{
                    System.out.println("going to index");
                    try {
                        request.setAttribute("message", "Invalid credentials.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("user set into sesssion");
            }
        }else{
            showHome(request,response,session);
        }


    }
    public void showHome(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer nb = (Integer) session.getAttribute("nbNotes");
        Vector<User> users = null;
        Vector<Note> notes = null;
        if(user == null) {
            try {
                response.sendRedirect(request.getContextPath() + "/auth");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            if (user.isAdmin()) {
                users = userService.getUsers();
                request.setAttribute("users", users);
                try {
                    request.getRequestDispatcher("Admin/admin.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    if(nb == null){
                        session.setAttribute("nbNotes", 0);
                    }
                    System.out.println("this is nb notes" + nb);
                    notes = noteService.getNotes(user.getEmail());
                    request.setAttribute("notes", notes);
                    request.getRequestDispatcher("User/user.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache,no-store");
        response.setDateHeader("Expires", -1);
        response.setHeader("Pragma", "no-cache");
        session.invalidate();
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (request.getParameter("email") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("pwd");
            String name = request.getParameter("name");
            String isAdmin = request.getParameter("isAdmin");
            boolean admin = isAdmin.equals("admin");
            User user = new User();
            user.setEmail(email);
            user.setPwd(password);
            user.setName(name);
            user.setAdmin(admin);
            boolean done = userService.editUser(user);
            if (done) {
                session.setAttribute("message", "Success!");
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                session.setAttribute("message", "Erreur veuillez réessayer!");
                try {
                    response.sendRedirect(request.getContextPath() + "/auth");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            session.setAttribute("message", "Erreur veuillez réessayer!");
            showHome(request, response,session);
        }
    }

}
