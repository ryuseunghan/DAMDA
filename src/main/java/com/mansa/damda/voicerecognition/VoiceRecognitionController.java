package com.mansa.damda.voicerecognition;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequiredArgsConstructor
@RequestMapping("/voice-recognition")
@Api(tags = "voice recognition API")
public class VoiceRecognitionController {
    private final VoiceRecognitionService voiceRecognitionService;

    @PostMapping("/activating")
    public ResponseEntity<String> recognizeVoice(
            @RequestParam("voiceFile") MultipartFile voiceFile) {
            String result = voiceRecognitionService.recognizeVoice(voiceFile);
            return ResponseEntity.ok(result);
    }
}
