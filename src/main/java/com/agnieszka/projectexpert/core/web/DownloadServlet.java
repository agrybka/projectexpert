package com.agnieszka.projectexpert.core.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="*.file", name="DownloadServlet")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String path=req.getParameter("path");//pobranie sciezki do pliku
		String name=req.getParameter("name");
		
		File file=new File(path);
		if(name.endsWith(".txt"))
			resp.setContentType("text/plain");//MIME type
		else if(name.endsWith(".pdf"))//pdf
			resp.setContentType("application/pdf");
		else if(name.endsWith(".docx"))//docx
			resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		else if(name.endsWith("doc"))//doc
			resp.setContentType("application/msword");
		else if(name.endsWith(".png"))//png
			resp.setContentType("image/png");
		else if(name.endsWith(".jpg"))//jpg
			resp.setContentType("image/jpeg");
		else
			resp.setContentType("text/plain");//MIME type
		
		
		resp.setHeader ("Content-Disposition", String.format("attachment;filename=\"%s\"",URLEncoder.encode(name, "UTF-8").replace("+", "%20")));
		
		InputStream input=null;
		OutputStream output=null;
		try
		{
			input=new FileInputStream(file);
			output= resp.getOutputStream();
			int sum=0;
			int read;
			byte[] buffor=new byte[1024];
			while((read=input.read(buffor))!=-1)//odczytujemy bajty z wstrumienia wejsciowego
			{
				output.write(buffor, 0, read);//wyrzucamy bajty do strumienia wyjsciowego(przegladarki)
				sum+=read;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(input!=null)
				input.close();
			if(output!=null)
				output.close();
		
		}
		
		
	}

	
}
