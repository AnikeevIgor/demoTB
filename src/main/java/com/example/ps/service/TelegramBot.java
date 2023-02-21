package com.example.ps.service;

import com.example.ps.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "начать"));
        listOfCommands.add(new BotCommand("/f1", "Узнать информацию о приюте"));
        listOfCommands.add(new BotCommand("/f2", "Как взять собаку из приюта "));
        listOfCommands.add(new BotCommand("/f3", "Прислать отчет о питомце"));
        listOfCommands.add(new BotCommand("/help", "Позвать волонтера"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bots command " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {

                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;

                case "/f1":
                    f1(chatId);
                    break;

                case "/f2":
                    f2(chatId);
                    break;

                case "/help":
                    sendMessage(chatId, "Волнер свяжется с вами в ближайшее время");
                    break;

                default:
                    sendMessage(chatId, "no");
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (callbackData.equals("ff1")) {
                String text = "poka norm";
                executeEditMessageText(text, chatId, messageId);// тут нужен свич
            }
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = "hi, " + name;
        log.info("Replied to user " + name);

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId((String.valueOf(chatId)));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void f1(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("о нас");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine4 = new ArrayList<>();
        var ff1 = new InlineKeyboardButton();

        ff1.setText("о приюте");
        ff1.setCallbackData("ff1");

        var ff2 = new InlineKeyboardButton();

        ff2.setText("рассписание работы, адрес и схема проезда");
        ff2.setCallbackData("ff2");

        var ff3 = new InlineKeyboardButton();

        ff3.setText("общие рекомендации о техники безопасности на территории приюта");
        ff3.setCallbackData("ff3");

        var ff4 = new InlineKeyboardButton();

        ff4.setText(" записать контактные данные для связи");
        ff4.setCallbackData("ff4");

        var help = new InlineKeyboardButton();

        help.setText("позвать волонтера");
        help.setCallbackData("ff3");

        rowInLine.add(ff1);
        rowInLine1.add(ff2);
        rowInLine2.add(ff3);
        rowInLine4.add(ff4);
        rowInLine3.add(help);


        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);
        rowsInLine.add(rowInLine4);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message);
    }


    private void f2(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("как подготовится к усыновлению");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine4 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine5 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine6 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine7 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine8 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine9 = new ArrayList<>();


        var f2f1 = new InlineKeyboardButton();

        f2f1.setText(" правила знакомства с собакой до того, как можно забрать ее из приюта");
        f2f1.setCallbackData("f2f1");

        var f2f2 = new InlineKeyboardButton();

        f2f2.setText("список документов, необходимых для того, чтобы взять собаку из приюта");
        f2f2.setCallbackData("f2f2");

        var f2f3 = new InlineKeyboardButton();

        f2f3.setText(" список рекомендаций по транспортировке животного");
        f2f3.setCallbackData("f2f3");

        var f2f4 = new InlineKeyboardButton();

        f2f4.setText("список рекомендаций по обустройству дома для щенка");
        f2f4.setCallbackData("f2f4");

        var f2f5 = new InlineKeyboardButton();

        f2f5.setText("список рекомендаций по обустройству дома для собаки \n с ограниченными возможностями (зрение, передвижение)");
        f2f5.setCallbackData("f2f5");

        var f2f6 = new InlineKeyboardButton();

        f2f6.setText("советы кинолога по первичному общению с собакой");
        f2f6.setCallbackData("f2f6");

        var f2f7 = new InlineKeyboardButton();

        f2f7.setText("рекомендации по проверенным кинологам для дальнейшего обращения к ним.");
        f2f7.setCallbackData("f2f7");

        var f2f8 = new InlineKeyboardButton();

        f2f8.setText("список причин, почему могут отказать и не дать забрать собаку из приюта");
        f2f8.setCallbackData("f2f8");

        var f2f9 = new InlineKeyboardButton();

        f2f9.setText("принять и записать контактные данные для связи");
        f2f9.setCallbackData("f2f9");

        var help = new InlineKeyboardButton();

        help.setText("позвать волонтера");
        help.setCallbackData("ff3");

        rowInLine.add(f2f1);
        rowInLine1.add(f2f2);
        rowInLine2.add(f2f3);
        rowInLine3.add(f2f4);
        rowInLine4.add(f2f5);
        rowInLine5.add(f2f6);
        rowInLine6.add(f2f7);
        rowInLine7.add(f2f8);
        rowInLine8.add(f2f9);
        rowInLine9.add(help);


        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);
        rowsInLine.add(rowInLine4);
        rowsInLine.add(rowInLine5);
        rowsInLine.add(rowInLine6);
        rowsInLine.add(rowInLine7);
        rowsInLine.add(rowInLine8);
        rowsInLine.add(rowInLine9);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message);
    }

    private void vk(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        row.add("weather");
        row.add("get random joke");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("register");
        row.add("check my data");
        row.add("delete my data");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("oshibka" + e.getMessage());
        }
    }

    private void executeEditMessageText(String text, long chatId, long messageId) {
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("no1" + e.getMessage());
        }
    }

}
