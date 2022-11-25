

void setup()
{
  Serial.begin(9600);
  InitI2C();
}

int data[2];

void loop()
{
  //Serial.print("\n\n--- NEW DATA ---\n\n");
  readSensor();

//
//  for (int i=1;i<256;i++)
//  {
//    uint8_t d = getData(i);
//    if(d!=0)
//    {
//      Serial.print(i);
//      Serial.print(" ");
//      Serial.println(d);
//    }
//  }
//  delay(500);
}
