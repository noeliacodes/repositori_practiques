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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}