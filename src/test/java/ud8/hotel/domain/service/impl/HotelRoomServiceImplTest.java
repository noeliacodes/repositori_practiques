package ud8.hotel.domain.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ud8.common.exception.ResourceNotFoundException;
import ud8.hotel.domain.entity.HotelRoom;
import ud8.hotel.domain.service.NotificationService;
import ud8.hotel.persistance.repository.HotelRoomRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class HotelRoomServiceImplTest {

    @Mock
    private HotelRoomRepository repository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private HotelRoomServiceImpl service;

@Nested
    class findByCodeTests{

    @Test
    void givenExistingRoomShouldReturnTheRoom() throws ResourceNotFoundException {
        //Arrange
        HotelRoom roomExpected = new HotelRoom("HR1", 30.0);
        when(repository.findByCode(roomExpected.getCode())).thenReturn(roomExpected);
        //Act
        HotelRoom roomActual = service.findByCode(roomExpected.getCode());
        //Assert
        assertAll(

                ()-> assertEquals(roomExpected, roomActual),
                ()-> verify(repository).findByCode(roomExpected.getCode())


        );


    }

    @Test
    void givingNonExistingRoomShouldThrowException(){
        HotelRoom NonExistingRoom = new HotelRoom("HRxxx", 30.0);
        when(repository.findByCode(NonExistingRoom.getCode())).thenReturn(null);

        //assert
        assertThrows(ResourceNotFoundException.class,()-> service.findByCode(NonExistingRoom.getCode()));



    }


}

@Nested
    class CreateTests{

    @Test
    void aRoomIsCreated_shouldreturnRoom(){
        //arrange
        HotelRoom latest = new HotelRoom("HAB007", 67.0);
        when(repository.latest()).thenReturn(latest);
        //act
        HotelRoom newRoom = service.create();

        //assert
        assertAll(
                ()-> assertNotNull(newRoom),
                ()-> verify(repository).save(newRoom),
                ()-> verify(notificationService).sendNotification(newRoom, "Your new hotel room has been registered!")


        );




    }




}

@Nested
    class UpdateTests{

    @Test
    void givenNonExistingRoomShouldReturnFalse_SoNOUpdate(){
        //arrange
        HotelRoom nonExistingRoom = new HotelRoom("HAB001", 30.5);
        when(repository.existsByCode(nonExistingRoom.getCode())).thenReturn(false);//com no exosteix no s'actualitza
        boolean expected = false;
        //act
            boolean actual = service.update(nonExistingRoom);
        //assert
            assertAll(
                    ()-> assertEquals(expected,actual),
                    ()-> verify(repository,never()).save(nonExistingRoom)

            );



    }

    @Test
    void givenExistingRoomShouldReturnTrue_SoNOUpdate(){
        //arrange
        HotelRoom existingRoom = new HotelRoom("HAB333", 30.2);
        when(repository.existsByCode(existingRoom.getCode())).thenReturn(true);
        boolean expected = true;
        //act
        boolean actual = service.update(existingRoom);
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> verify(repository).save(existingRoom)

        );


    }



}

@Nested
    class DeleteTests{
    @Test
    void givenNonExistingCode_NOUpdate_returnFalse(){
    //arrange
        HotelRoom nonExistingRoom = new HotelRoom("HAB1313");
        when(repository.existsByCode(nonExistingRoom.getCode())).thenReturn(false);
        boolean expected = false;
    //act
         boolean actual = service.delete(nonExistingRoom.getCode());
    //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> verify(repository,never()).delete(nonExistingRoom.getCode())

        );


    }

    @Test
    void givenExistingCode_Update_returnTrue(){
        //arrange
        HotelRoom existingRoom = new HotelRoom("HAB1313");
        when(repository.existsByCode(existingRoom.getCode())).thenReturn(true);
        boolean expected = true;
        //act
        boolean actual = service.delete(existingRoom.getCode());
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> verify(repository).delete(existingRoom.getCode())

        );


    }

}


@Nested
    class AddChargeTests{
    @Test
    void givenNegativeAmount_shouldReturnFalse_noAddCharge(){
        //arrange
        HotelRoom room = new HotelRoom("HAB123", 20);
        double amount = -2.0;
        boolean expected= false;

        //act

        boolean actual = service.addCharge(room,amount);
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> verify(repository,never()).save(room),
                ()-> assertEquals(20,room.getConsumption())


        );



    }

    @Test
    void RightAmount_shouldReturnTrue_soAddCharge(){
        //arrange
        HotelRoom room = new HotelRoom("HAB123", 20);
        double amount = 20.23;
        boolean expected= true;
        double expectedConsumption= room.getConsumption()+amount;
        //act

        boolean actual = service.addCharge(room,amount);
        double actualConsumption = room.getConsumption();
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> verify(repository).save(room),
                ()-> assertEquals(expectedConsumption,actualConsumption)


        );




    }


}

@Nested
    class PayChargeTests{
    @Test
    void givenNegativeAmount_shouldReturnFalse_SoNOPay(){
    //arrange
        HotelRoom room = new HotelRoom("HAB123", 30);
        double amount = -1.3;
        boolean expected=false;
        double consumptionExpected=30;

    //act
        boolean actual = service.payCharge(room,amount);
        double consumptionActual = room.getConsumption();
    //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> assertEquals(consumptionExpected,consumptionActual),
                ()-> verify(repository,never()).save(room)


        );

    }

    @Test
    void given0Amount_shouldReturnFalse_SoNOPay(){
        //arrange
        HotelRoom room = new HotelRoom("HAB123", 30);
        double amount = 0;
        boolean expected=false;
        double consumptionExpected=30;

        //act
        boolean actual = service.payCharge(room,amount);
        double consumptionActual = room.getConsumption();
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> assertEquals(consumptionExpected,consumptionActual),
                ()-> verify(repository,never()).save(room)


        );

    }

    @Test
    void givenMoreAmountThanConsumption_shouldReturnFalse_SoNOPay(){
        //arrange
        HotelRoom room = new HotelRoom("HAB123", 30);
        double amount = 32;
        boolean expected=false;
        double consumptionExpected=30;

        //act
        boolean actual = service.payCharge(room,amount);
        double consumptionActual = room.getConsumption();
        //assert
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> assertEquals(consumptionExpected,consumptionActual),
                ()-> verify(repository,never()).save(room)


        );

    }



}
}