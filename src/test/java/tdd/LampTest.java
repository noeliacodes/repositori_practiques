package tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LampTest {
//    @Test
//    void LampClassShouldExist(){
//    Lamp lamp = new Lamp();
//
//    }

    @Nested
    @DisplayName("Test Constructor")
    class Constructor{
        @Test
        @DisplayName("Constructor with parameter -> double and boolean")
        void ConstructorShouldHaveDoubleAndBooleanParameters(){
            //
            double consumption=0.5;
            boolean isOn=true;

            //act
            Lamp lamp = new Lamp (consumption,isOn);


        }

        @Test
        @DisplayName("Constructor should assign values to atributes")
        void ConstructorShouldAssignValuesToAtributes(){
            //arrange
            double consumptionExpected=0.5;
            boolean isOnExpected=true;

            //act
            Lamp lamp = new Lamp (consumptionExpected,isOnExpected);

            //Assert
            assertAll(
                    ()-> assertEquals(consumptionExpected,lamp.getConsumption()),
                    ()->assertEquals(isOnExpected, lamp.isOn())




            );

        }

    }


    @Nested
    @DisplayName("Method: turn on()")
    class TurnOn{
        @Test
        @DisplayName("Mètode TurnOn ha d'existir")
        void methodTurnOnShouldExist(){
            //arrange
            Lamp lampara = new Lamp (34.3,true);

            //act
            lampara.turnOn();

        }
        @Test
        @DisplayName("TurnOn funcionalidad. TurON cambia a true el is On ")
        void TurnOnCambiaElIsOnAtrueCuandoApagada(){
            //arrange

            boolean expected=true;
            //act
            Lamp lampara = new Lamp (34.3,false);
             lampara.turnOn();
             boolean actual = lampara.isOn();
            //assert
            assertEquals(expected,actual);

        }

    }

    @Nested
    @DisplayName("Method: turn off")
    class TurnOff{
        @Test
        @DisplayName("Mètode TurnOff ha d'existir")
        void methodTurnOffShouldExist(){
            //arrange
            Lamp lampara = new Lamp (34.3,true);

            //act
            lampara.turnOff();

        }
        @Test
        @DisplayName("TurnOff funcionalidad. TurnOff cambia a false el is On ")
        void TurnOFFApagaLampara(){
            //arrange

            boolean expected=false;
            //act
            Lamp lampara = new Lamp (34.3,true);
            lampara.turnOff();
            boolean actual = lampara.isOn();
            //assert
            assertEquals(expected,actual);

        }


    }

    @Nested
    @DisplayName("Method: toggle")
    class Toggle{
        @Test
        @DisplayName("Toggle should exist")
        void ToggleShouldExist(){
            //
            Lamp lampara = new Lamp (34.5, true);

            lampara.toggle();


        }
        @Test
        @DisplayName(" case 1 - Funcionalidad Toggle. Si esta encendido -> apaga")
        void ToggleShouldOffifOn(){
            //arrange
            Lamp lampara = new Lamp (34.5, true); //esta encendido
            boolean expected=false;
            //act
            lampara.toggle();
            boolean actual= lampara.isOn();
            //assert
            assertEquals(expected, actual);


        }
        @Test
        @DisplayName(" case 2 - Funcionalidad Toggle. Si esta apagado  -> lo enciende")
        void ToggleShouldONifOFF(){
            //arrange
            Lamp lampara = new Lamp (34.5, false); //esta apagado
            boolean expected=true;
            //act
            lampara.toggle();
            boolean actual= lampara.isOn();
            //assert
            assertEquals(expected, actual);


        }



    }
/**/
    @Nested
    @DisplayName("Metodo: calculateConsumption(seconds: double): double")
    class CalculateConsumption{

       /* @Test
        @DisplayName("Método tiene que existir")
        void CalculateConsumptionShouldExist(){
            Lamp lampara = new Lamp (23.08, false);

            lampara.calculateConsumption();

        }


        */

    @Test
    @DisplayName("Metódo tiene parametro de entrada double")
     void calculateParametroEntrada(){
        Lamp lampara = new Lamp (23.08, false);
        double entrada=4.34;
        lampara.calculateConsumption(entrada);

    }

    @Test
    @DisplayName("Método tiene que devolver-> double")
    void calculateShouldReturnDouble(){
        Lamp lampara = new Lamp (23.08, false);
        double entrada=4.34;
        double returned= lampara.calculateConsumption(entrada);

    }

    @Test
    @DisplayName("CASO 1 : lampara apagada consumo 0 ")
    void prueba1(){
        //arrange
        double consumoExpected=0.0;
        Lamp lampara = new Lamp (9898.090, false);
        double secondsIn=70;
        //act
        double consumoActual =lampara.calculateConsumption(secondsIn);
        //assert
        assertEquals(consumoExpected,consumoActual,0.001);


    }

    @Test
    @DisplayName("CASO 2: lampara encendida consumo ")
    void prueba2(){
        //arrange
        double consumoExpected=25.2;
        Lamp lampara = new Lamp (25.2, true);
        double secondsIn=3600;
        //act
        double consumoActual =lampara.calculateConsumption(secondsIn);
        //assert
        assertEquals(consumoExpected,consumoActual, 0.001);


    }
}

}
