package com.pinyougou.controller.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pinyougou.common.ApiResult;

import util.FileUtils;
import util.UUIDUtils;


/**
 * @desc:文件上传
 * @author:yue
 * @date:2019.6.10
 */
@Controller
@RestController
@RequestMapping("/upload")
public class UploadController {
	
	
	@RequestMapping(value={"/uploadFile"})
	public ApiResult uploadFile(MultipartFile uploadFile,HttpServletRequest request){
		try {  
			   if(uploadFile==null){
				   return new ApiResult(201,"请上传文件","");
			   }
			   String originalFilename = uploadFile.getOriginalFilename();
			   String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			   String uploadPath;
			   if(FileUtils.isImgFile(ext)){
				   uploadPath = "/smallPicUpload";
			   }else if(FileUtils.isVideoFile(ext)){
				   uploadPath = "/itemVideoUpload";
			   }else{
				   uploadPath = "/fileUpload";
			   }
			   String savePath = request.getSession().getServletContext().getRealPath("/")+uploadPath;
	           String saveFileName = UUIDUtils.getRandomImgName()+ext;
	           File filePath = new File(savePath);
			   if (!filePath.exists() && !filePath.isDirectory()) {
			      filePath.mkdir();
			    }
			  File saveFile = new File(savePath,saveFileName);
			  InputStream in = uploadFile.getInputStream();
			  FileOutputStream out = new FileOutputStream(saveFile);
			  byte buffer[] = new byte[1024];
			  int len = 0;
			  while((len=in.read(buffer))>0){
				   out.write(buffer, 0, len);
			   }
			  in.close();
			  out.close();
			String responseFilePath = uploadPath+"/"+saveFileName;
			return new ApiResult(200, "文件上传成功！", responseFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "文件上传失败！", "");
		}
	}
	
	@RequestMapping(value="/uploadItemImages",method = RequestMethod.POST)
	public ApiResult uploadItemImages(@RequestParam("itemImage")MultipartFile[] itemImages,HttpServletRequest request){
		try {
			  String itemImagesStr = "";
			  if(itemImages==null||itemImages.length==0){
				  return new ApiResult(201, "请上传文件！", "");
			  }else{
					 for(int i=0;i<itemImages.length;i++){
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
						  itemImagesStr += "/itemImagesUpload/"+saveFileName;
					  }else{
						  itemImagesStr += "/itemImagesUpload/"+saveFileName+",";
					  }
				    }
			   String[] itemImgPathArray = itemImagesStr.split(",");
			   return new ApiResult(200, "文件上传成功！",itemImgPathArray);
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "文件上传失败！", "");
		}
	}
}
