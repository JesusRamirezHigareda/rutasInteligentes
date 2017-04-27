#include <SPI.h>
#include <Ethernet.h>
#include <SoftwareSerial.h>
#include <TinyGPS.h>

// variables ethernet shield
byte mac[] = {  0x90, 0xA2, 0xDA, 0x0D, 0x9A, 0x47 };
IPAddress ip(169,254,215,150);
IPAddress serverIP(169,254,215,149);
EthernetClient client;
int serverPort=9000;
int msg;

TinyGPS gps;
SoftwareSerial ss(4, 3);

// variables sensor
float latitud;
float longitud;
String hora;

void setup() {
Serial.begin(115200);
  ss.begin(9600);
  
  //hora
  
  
  Serial.print("Rutas Inteligentes"); Serial.println(TinyGPS::library_version());
  Serial.println("Monitoreo GPS");
  Serial.println();
  
  if (Ethernet.begin(mac) == 0) {
    Serial.println("No se pudo configurar con dhpc, cambiando a ip statica");
    Ethernet.begin(mac, ip);
  }
  Serial.println(Ethernet.localIP());

  delay(1000);
  Serial.println("Connectando al servidor ...");
  
  if (client.connect(serverIP, serverPort)) {
    Serial.println("Se pudo conectar con ip statica mensaje de prueba");
  }
  else {
   Serial.println("Coneccion fallida");
  }
}

void loop() {

 bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;

  // For one second we parse GPS data and report some key values
  for (unsigned long start = millis(); millis() - start < 1000;)
  {
    while (ss.available())
    {
      char c = ss.read();
      // Serial.write(c); // uncomment this line if you want to see the GPS data flowing
      if (gps.encode(c)) // Did a new valid sentence come in?
        newData = true;
    }
  }
    if (newData)
  {
    float flat, flon;
    unsigned long age;
    gps.f_get_position(&flat, &flon, &age);
    
    latitud = flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6;
   
    longitud = flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6;
   
  }

  client.stop();
  if(client.connect(serverIP, serverPort)){
    String json = "{\"IdUnit\" :\"ABC-1234\", \"Locations\":{\"Latitude\" : \""+String(latitud)+"\", \"Longitude\": \""+String(longitud)+"\" }}";
    Serial.println(json);
    client.println(json); 
    client.flush();
  }
  else {
   Serial.println("Coneccion fallida");
  }
  delay(5000);       
}

