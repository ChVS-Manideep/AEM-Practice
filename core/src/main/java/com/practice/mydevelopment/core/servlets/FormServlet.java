package com.practice.mydevelopment.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.api.resource.ResourceResolver;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods="+ HttpConstants.METHOD_POST,
                "sling.servlet.paths="+"/apps/my/servlets/path/create/formData"
        }
)
public class FormServlet extends SlingAllMethodsServlet {

    private static final String CREATE_PATH = "/content/usergenerated/formData";

    @Reference
    private SlingRepository repository;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {

            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            System.out.println("firstName"+firstName);
            Node mainNode = session.getNode(CREATE_PATH);
            Node subNode = mainNode.addNode(firstName, "nt:unstructured");
            subNode.setProperty("First Name", firstName);
            subNode.setProperty("Last Name", lastName);
            subNode.setProperty("Email", email);
            subNode.setProperty("Mobile Number", phoneNumber);
            subNode.setProperty("Gender", gender);
            session.save();

            out.println("Data has been submitted successfully....");
        } catch (RepositoryException e) {
            out.println("Error occurred while submitting data.");
            e.printStackTrace(out);
        }

        out.flush();
        out.close();
    }
}
