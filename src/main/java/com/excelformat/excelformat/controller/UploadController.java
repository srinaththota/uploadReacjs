package com.excelformat.excelformat.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.excelformat.excelformat.service.ExcelReadServiceImpl;

@Controller
public class UploadController {
	
	@Autowired
	Environment env;
	

	@RequestMapping(value="/check", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> upload(){
		return new ResponseEntity<>("Hello upload",HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam(value="name") MultipartFile uploadFile){
		
		try{
			String filename=uploadFile.getOriginalFilename();
			String dir=env.getProperty("upload.file.path");
			String uploadfilepath=Paths.get("D:\\"+dir, filename).toString();
			
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File(uploadfilepath)));
			stream.write(uploadFile.getBytes());
			stream.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String data=new ExcelReadServiceImpl().sendCsv();
		return new ResponseEntity<>(data,HttpStatus.OK);
	}

	
}
