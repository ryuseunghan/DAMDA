package com.mansa.damda.voicerecognition;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class VoiceRecognitionService {

    public String recognizeVoice(MultipartFile voiceFile) {
        String clientId = "39x8q030yy";             // 발급받은 Client ID
        String clientSecret = "ZVP2tKqv3uXoVjRkqPcgi5cU2bDDnvE16CFQeucC";     // 발급받은 Client Secret

        try {
            // 음성 파일 저장 및 처리
            File tempVoiceFile = File.createTempFile("voice_", ".wav");
            voiceFile.transferTo(tempVoiceFile);

            String language = "Kor";
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");  // POST 메서드 사용
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(tempVoiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString(); // 응답 문자열 반환
            } else {  // 오류 발생
                System.out.println("error responseCode= " + responseCode);
            }
        } catch (Exception e) {
            System.out.println(e);
            // 예외 처리
        }
        return null; // 처리 실패 시 null 반환
    }
}