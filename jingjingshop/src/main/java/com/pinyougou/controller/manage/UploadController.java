package com.pinyougou.controller.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pinyougou.common.ApiResult;

import util.UUIDUtils;

@Controller
@RestController
@RequestMapping("/upload")
public class UploadController {
	
	
	@RequestMapping(value={"/uploadFile"})
	public ApiResult uploadFile(MultipartFile smallPic,HttpServletRequest request){
		try {  
			   if(smallPic==null){
				   return new ApiResult(201,"请上传文件","");
			   }
			   String savePath = request.getSession().getServletContext().getRealPath("/")+"/smallPicUpload";
	           String originalFilename = smallPic.getOriginalFilename();
	           String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
	           String saveFileName = UUIDUtils.getRandomImgName()+ext;
	           File filePath = new File(savePath);
			   if (!filePath.exists() && !filePath.isDirectory()) {
			      filePath.mkdir();
			    }
			  File saveFile = new File(savePath,saveFileName);
			  InputStream in = smallPic.getInputStream();
			  FileOutputStream out = new FileOutputStream(saveFile);
			  byte buffer[] = new byte[1024];
			  int len = 0;
			  while((len=in.read(buffer))>0){
				   out.write(buffer, 0, len);
			   }
			  in.close();
			  out.close();
			return new ApiResult(200, "文件上传成功！", "/smallPicUpload/"+saveFileName);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "文件上传失败！", "");
		}
	}
	
	@RequestMapping(value="/uploadItemImages",method = RequestMethod.POST)
	public ApiResult uploadItemImages(@RequestParam("itemImage")MultipartFile[] itemImages,HttpServletRequest request){
		try {
			  System.out.println(itemImages.length);
			  String itemImagesStr = "";
			  if(itemImages==null||itemImages.length==0){
				  return new ApiResult(201, "请上传文件！", "");
			  }else{
					 for(int i=0;i<itemImages.length;i++){
					   String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
					   String savePath = request.getSession().getServletContext().getRealPath("/")+"/itemImagesUpload";
			           String originalFilename = itemImages[i].getOriginalFilename();
			           String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			           String saveFileName = UUIDUtils.getRandomImgName()+ext;
			           File filePath = new File(savePath);
					   if (!filePath.exists() && !filePath.isDirectory()) {
					      filePath.mkdir();
					    }
					   File saveFile = new File(savePath,saveFileName);
					   if(originalFilename==null || originalFilename.trim().equals("")){
						   return new ApiResult(201,"上传文件不存在","");
					    }
					  InputStream in = itemImages[i].getInputStream();
					  FileOutputStream out = new FileOutputStream(saveFile);
					  byte buffer[] = new byte[1024];
					  int len = 0;
					  while((len=in.read(buffer))>0){
						   out.write(buffer, 0, len);
					   }
					  in.close();
					  out.close();
					  if(i==itemImages.length-1){
						  itemImagesStr += saveFile.getPath();
					  }else{
						  itemImagesStr += saveFile.getPath()+",";
					  }
				    }
			   return new ApiResult(200, "文件上传成功！", itemImagesStr);
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "文件上传失败！", "");
		}
	}
}
