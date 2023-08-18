package com.mansa.damda.voicerecognition;

import com.mansa.damda.product.ProductRepository;
import com.mansa.damda.store.StoreRepository;
import com.mansa.damda.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class VoiceRecognitionService {
    private final StoreRepository storeRepository;

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
            conn.setRequestMethod("POST");
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
            } else {
                System.out.println("error responseCode= " + responseCode);
            }
        } catch (Exception e) {
           throw new IllegalArgumentException("음성 인식 오류");
        }
        return null;
    }



    public VoiceRecognitionDTO voiceRecognitionFunction(VoiceRecognitionInputDTO voiceRecognitionInputDTO){
        VoiceRecognitionDTO voiceRecognitionDTO = new VoiceRecognitionDTO();
        Long userId = voiceRecognitionInputDTO.getUserId();
        String string = voiceRecognitionInputDTO.getSearchString();
        int flag = 0;
        switch(string){
            case "내 가게": {
                try{
                    Long storeId = storeRepository.findByUserUserId(userId).orElseThrow(() -> new IllegalArgumentException("사용자의 가게를 찾을 수 없습니다.")).getStoreId();
                    voiceRecognitionDTO.setStatusCode(0L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(storeId); //사용하기
                    voiceRecognitionDTO.setSearchString(string);
                    break;
                }
                catch(IllegalArgumentException e){
                    voiceRecognitionDTO.setStatusCode(-1L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(null);
                    voiceRecognitionDTO.setSearchString(string);
                    break;
                }
            }
            case "네 가게":{
                try{
                    Long storeId = storeRepository.findByUserUserId(userId).orElseThrow(() -> new IllegalArgumentException("사용자의 가게를 찾을 수 없습니다.")).getStoreId();
                    voiceRecognitionDTO.setStatusCode(1L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(storeId); //사용하기
                    voiceRecognitionDTO.setSearchString(string);
                    break;
                }
                catch(IllegalArgumentException e){
                    voiceRecognitionDTO.setStatusCode(-1L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(null);
                    voiceRecognitionDTO.setSearchString(string);
                    break;
                }
            }
            case "마이페이지":{
                voiceRecognitionDTO.setStatusCode(2L);
                voiceRecognitionDTO.setUserId(userId);
                voiceRecognitionDTO.setStoreId(null);
                voiceRecognitionDTO.setSearchString(string);
                break;
            }
            default:{
                try{

                    Long storeId = storeRepository.findByUserUserId(userId).orElseThrow(() -> new IllegalArgumentException("사용자의 가게를 찾을 수 없습니다.")).getStoreId();
                    voiceRecognitionDTO.setStatusCode(3L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(storeId); //사용하기
                    voiceRecognitionDTO.setSearchString(string);
                }catch(IllegalArgumentException e){
                    flag = 1;
                    voiceRecognitionDTO.setStatusCode(-1L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(null); //사용하기
                    voiceRecognitionDTO.setSearchString(string);
                }
                if(flag == 1) {
                    voiceRecognitionDTO.setStatusCode(4L);
                    voiceRecognitionDTO.setUserId(userId);
                    voiceRecognitionDTO.setStoreId(null);
                    voiceRecognitionDTO.setSearchString(string);//사용하기
                }
            }



        }
        return voiceRecognitionDTO;
    }

}