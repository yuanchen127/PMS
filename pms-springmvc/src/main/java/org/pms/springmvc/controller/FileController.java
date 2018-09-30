package org.pms.springmvc.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author ike
 *
 */

@Controller
@RequestMapping("/file")
public class FileController {
	
	private static final String FILE_SEPARATOR = File.separator;
	
	@RequestMapping("/")
	public String fileIndex() {
		return "file/uploadFile";
	}
	
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public String fileUpload(HttpSession session,@RequestParam(value="description",required=false) String description,@RequestParam("file") MultipartFile file) {
		if(file!=null) {
			String fileName = file.getOriginalFilename();
			String path = session.getServletContext().getRealPath("/WEB-INF/download");
			System.out.println("file:" + path + fileName);
			File destFile = new File(path,fileName);
			if(!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			try {
				file.transferTo(new File(path + FILE_SEPARATOR + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("file upload success!");
			return "../../success";
		}else {
			System.out.println("file upload failed!");
			return "../../error";
		}
	}

}
