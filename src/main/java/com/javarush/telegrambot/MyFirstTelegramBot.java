package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "javarush_demo_mentor_02_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "7176627565:AAFFMLGpJ-MTBZksTD7_9XQ-WpQuhlk05ug"; //TODO: добавьте токен бота в кавычках
    public static final String START = "/start";
    public static final String BUTTON_STEP_1 = "step1_Button";
    public static final String BUTTON_STEP_2 = "step2_Button";
    public static final String BUTTON_STEP_3 = "step3_Button";
    public static final String BUTTON_STEP_4 = "step4_Button";
    public static final String BUTTON_STEP_5 = "step5_Button";
    public static final String BUTTON_STEP_6 = "step6_Button";
    public static final String BUTTON_STEP_7 = "step7_Button";
    public static final String BUTTON_STEP_8 = "step8_Button";
    public static final String PICTURE_1 = "step_1_pic";
    public static final String PICTURE_2 = "step_2_pic";
    public static final String PICTURE_3 = "step_3_pic";
    public static final String PICTURE_4 = "step_4_pic";
    public static final String PICTURE_5 = "step_5_pic";
    public static final String PICTURE_6 = "step_6_pic";
    public static final String PICTURE_7 = "step_7_pic";
    public static final String PICTURE_8 = "step_8_pic";
    public static final String BUTTON_NAME_1 = "Fridge hacking";
    public static final String BUTTON_NAME_2 = "Eat a sausage! +20 glories";
    public static final String BUTTON_NAME_3 = "Eat a fish! +20 glories";
    public static final String BUTTON_NAME_4 = "Drop pickles! +20 glories";
    public static final String BUTTON_NAME_5 = "Vacuum-bot hacking";
    public static final String BUTTON_NAME_6 = "Make Vacuum-bot to bring food. +30 glories";
    public static final String BUTTON_NAME_7 = "Ride on the Vacuum-bot. +30 glories";
    public static final String BUTTON_NAME_8 = "Switch off the Vacuum-bot. +30 glories";
    public static final String BUTTON_NAME_9 = "Put on GoPro and turn it on";
    public static final String BUTTON_NAME_10 = "Make videos on the roof. +40 glories";
    public static final String BUTTON_NAME_11 = "Attack other cats with GoPro. +40 glories";
    public static final String BUTTON_NAME_12 = "Attack dogs with GoPro. +40 glories";
    public static final String BUTTON_NAME_13 = "Computer hacking";
    public static final String BUTTON_NAME_14 = "Go outside";


    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        if (getMessageText().equals(START)) {
            setUserGlory(0);
            sendPhotoMessageAsync(PICTURE_1);
            sendTextMessageAsync(STEP_1_TEXT, Map.of(BUTTON_NAME_1, BUTTON_STEP_1));
        }

        getReward(BUTTON_STEP_1, 20, PICTURE_2, STEP_2_TEXT, BUTTON_NAME_2, BUTTON_NAME_3, BUTTON_NAME_4, BUTTON_STEP_2);

        getNewStep(BUTTON_STEP_2, 20, PICTURE_3, STEP_3_TEXT, BUTTON_NAME_5, BUTTON_STEP_3);

        getReward(BUTTON_STEP_3, 30, PICTURE_4, STEP_4_TEXT, BUTTON_NAME_6, BUTTON_NAME_7, BUTTON_NAME_8, BUTTON_STEP_4);

        getNewStep(BUTTON_STEP_4, 30, PICTURE_5, STEP_5_TEXT, BUTTON_NAME_9, BUTTON_STEP_5);

        getReward(BUTTON_STEP_5, 40, PICTURE_6, STEP_6_TEXT, BUTTON_NAME_10, BUTTON_NAME_11, BUTTON_NAME_12, BUTTON_STEP_6);

        getNewStep(BUTTON_STEP_6, 40, PICTURE_7, STEP_7_TEXT, BUTTON_NAME_13, BUTTON_STEP_7);

        getNewStep(BUTTON_STEP_7, 50, PICTURE_8, STEP_8_TEXT, BUTTON_NAME_14, BUTTON_STEP_8);

        if (getCallbackQueryButtonKey().equals("step8_Button")) {
            addUserGlory(50);
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Glory amount: " + String.valueOf(getUserGlory()));
        }
    }

    private void getNewStep(String stepButton, int glory, String picture, String text, String buttonName, String nextButton) {
        if (getCallbackQueryButtonKey().equals(stepButton)) {
            addUserGlory(glory);
            sendPhotoMessageAsync(picture);
            sendTextMessageAsync(text,
                    Map.of(buttonName, nextButton));
        }
    }

    private void getReward(String stepButton, int glory, String picture, String text, String buttonName1, String buttonName2, String buttonName3, String nextButton) {
        if (getCallbackQueryButtonKey().equals(stepButton)) {
            addUserGlory(glory);
            sendPhotoMessageAsync(picture);
            sendTextMessageAsync(text,
                    Map.of(buttonName1, nextButton,
                            buttonName2, nextButton,
                            buttonName3, nextButton));
        }
    }


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}