package com.mansa.damda.voicerecognition;


import lombok.Data;

@Data
public class VoiceRecognitionDTO {
    Long statusCode;

    Long userId;

    Long storeId;

    String searchString;
}
