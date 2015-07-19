/**
 * Created by mipan on 08.06.2015.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import XMLConverter.XMLToCollection;
import exceptions.XMLAddingException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class Upload extends HttpServlet {

    private static final String OK_RESULT = "OK! All added ok";
    private static final String FAIL_RESULT = "FAIL! Something was wrong in adding xml to DB";


    private Random random = new Random();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().getAttribute("result");

        RequestDispatcher rd = request.getRequestDispatcher("upload.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = OK_RESULT;
        HttpSession session = request.getSession(true);
        session.setAttribute("result", result);

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        /*
        * create folder for upload
        * */
        String pathUPLD = request.getSession().getServletContext().getRealPath("/") + "upload/";

        try {
            File folderUPLD = new File(pathUPLD);
            if(!folderUPLD.isDirectory()) {
                folderUPLD.mkdirs();
//                folderUPLD.deleteOnExit();
            }
        } catch(Exception e)
        {
//            TODO logging
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();


        factory.setSizeThreshold(1024*1024);


        File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            String nameUplFile= null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    nameUplFile = processUploadedFile(item);

                }
            }


            String fullFileName = getServletContext().getRealPath("/") + "/upload/" + nameUplFile;
            try {
                XMLToCollection.XMLToCollection(fullFileName);
            }
            catch (XMLAddingException e){
                result = FAIL_RESULT;
                session.setAttribute("result", result);
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        doGet(request,response);
    }


    private String processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        String name;
        String randomName;
        do{
            name = new String(item.getName().getBytes(), "utf-8");
            randomName = random.nextInt() +"";
            String path = getServletContext().getRealPath("/upload/" + randomName + name);
            uploadetFile = new File(path);
        }while(uploadetFile.exists());

        uploadetFile.createNewFile();
        item.write(uploadetFile);
        return (randomName+name);
    }

}