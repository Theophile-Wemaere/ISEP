#define PWM_PIN A3

int pwm_value;

void setup() 
{
  pinMode(PWM_PIN, INPUT);
  Serial.begin(115200);
}
 
bool test = false;
float voc, co2;

void loop()
{
  pwm_value = pulseIn(PWM_PIN,HIGH);
  float percent = (pwm_value/1000)*100/33.3;
  if(test)
  {
    co2 = 40*percent-1800;
  }
  else
  {
    voc = 25*percent-125;
  }
  Serial.print(co2);
  Serial.print(" ");
  Serial.println(voc);
  test = !test;
}
