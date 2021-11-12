#include <ESP8266WiFi.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>   
#include <String.h>

const char *ssid = "123";           //WiFi 名稱
const char *password = "29678316";  //WiFi 密碼

const int IA = D8;  //  pin 5 connected to pin IA
const int IB = D9;  //  pin 6 connected to pin IB
const int IA1 = D0;
const int IB1 = D1;

const int red = D11;
const int blue = D12;
const int green = D13;

WiFiServer server(80); //聲明服務器對象
LiquidCrystal_I2C lcd(0x27,16,2);

void RGB_color(int red_light_value, int green_light_value, int blue_light_value)
 {
  analogWrite(red, red_light_value);
  analogWrite(green, green_light_value);
  analogWrite(blue, blue_light_value);
}

void setup()
{
    Serial.begin(115200);
    Serial.println();
    
    WiFi.mode(WIFI_STA);
    
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED)
    {
        delay(500);
        Serial.print(".");
    }
    Serial.println("Connected");
    Serial.print("IP Address:");
    Serial.println(WiFi.localIP());
    
    lcd.init();
    
    lcd.backlight();
    lcd.setCursor(1,0);
    lcd.setCursor(2,1);
    
    pinMode(IA, OUTPUT); // set pins to output
    pinMode(IB, OUTPUT);
    pinMode(IA1, OUTPUT); // set pins to output
    pinMode(IB1, OUTPUT); 
    digitalWrite(IA, LOW);
    digitalWrite(IB, LOW);
    digitalWrite(IA1, LOW);
    digitalWrite(IB1, LOW);

    pinMode(red, OUTPUT);
    pinMode(green, OUTPUT);
    pinMode(blue, OUTPUT);
    digitalWrite(red, HIGH);
    digitalWrite(green, HIGH);
    digitalWrite(blue, HIGH);
    
    server.begin(22333); //port端口 
}

void loop()
{
    WiFiClient client = server.available(); //嘗試建立服務對象
    if (client) //如果當前服務可用
    {
        Serial.println("[Client connected]");
        while (client.connected()) //如果客戶端處於連線狀態
        {
            if (client.available()) //如果有可讀資料
            {                     
                String val = client.readString();                                                                     
 
                if(val == "left"){
                   digitalWrite(IA, HIGH);
                   digitalWrite(IB, LOW);
                   digitalWrite(IA1, LOW);
                   digitalWrite(IB1, LOW);
                }

                if(val == "right"){
                   digitalWrite(IA, LOW);
                   digitalWrite(IB, LOW);
                   digitalWrite(IA1, HIGH);
                   digitalWrite(IB1, LOW);
                }
                   
                if(val == "forward"){
                   digitalWrite(IA, HIGH);
                   digitalWrite(IB, LOW);
                   digitalWrite(IA1, HIGH);
                   digitalWrite(IB1, LOW);
                }
                
                if(val == "backward"){
                  digitalWrite(IA, LOW);
                  digitalWrite(IB, HIGH);
                  digitalWrite(IA1, LOW);
                  digitalWrite(IB1, HIGH);
                }

                if(val == "stop"){
                  digitalWrite(IA, LOW);
                  digitalWrite(IB, LOW);
                  digitalWrite(IA1, LOW);
                  digitalWrite(IB1, LOW);
                }
               
                if(val == "off"){
                  digitalWrite(red, HIGH);
                  digitalWrite(green, HIGH);
                  digitalWrite(blue, HIGH);
                }
                if(val == "red"){
                  digitalWrite(red, LOW);
                  digitalWrite(green, HIGH);
                  digitalWrite(blue, HIGH);
                }
                
                if(val == "green"){
                  digitalWrite(red, HIGH);
                  digitalWrite(green, LOW);
                  digitalWrite(blue, HIGH);
                }
                
                if(val == "blue"){
                  digitalWrite(red, HIGH);
                  digitalWrite(green, HIGH);
                  digitalWrite(blue, LOW);
                }
          }
      }
      client.stop(); //结束當前連線:
      Serial.println("[Client disconnected]");
  }
}
