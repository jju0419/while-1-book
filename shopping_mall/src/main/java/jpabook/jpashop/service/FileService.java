package jpabook.jpashop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID(); //UUID는 서로 다른 개체들을 구별하기 위해서 이름을 부여할 때 사용한다.
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + extension;//UUID로 받은 값과 원래 파일의 이름이 확장자를 조합해서 저장될 파일 이름을 만든다.
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl); //FileOutputStream 클래스는 바이트 단위의 출력을 내보내는 클래스이다.
        fos.write(fileData); //fileData를 파일 출력 스트림에 입력한다.
        fos.close();
        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath); //파일이 저장된 경로를 이용하여 파일 객체를 생성한다.
        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}