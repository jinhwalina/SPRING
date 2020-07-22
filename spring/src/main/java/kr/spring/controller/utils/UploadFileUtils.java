package kr.spring.controller.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath, String originalName, byte[] 	
			fileData)throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() +"_" + originalName;
		String savedPath = calcPath(uploadPath);
		// C:Users\Administrator\Desktop\새 폴더\2020\07\22\ uuid_test.jpg ( 원래는 uuid 띄어써야함)
		// new File을 통해 이름을 가진 빈 파일이 생성이 된다.
		File target = new File(uploadPath + savedPath, savedName);
		// 파일 복사
		FileCopyUtils.copy(fileData, target);
		// 폴더 경로를 \가 아닌 /로 바꾸는 코드
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	
	private static String calcPath(String uploadPath) {
		// 업로드 날짜 정보를 생성
		Calendar cal = Calendar.getInstance();
		// 년도 경로를 생성 : \2020
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		// 년도와 월 경로를 생성 : \2020\07
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		// 년도와 월 일을 합친 일 경로를 생성 : \2020\07\22
		String datePath = monthPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
 
	}
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(uploadPath+paths[paths.length-1]).exists())
			return;
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if( !dirPath.exists())
				dirPath.mkdir();
		}
	}
	private static String makeIcon(String uploadPath, String path, String fileName)
        	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
}
