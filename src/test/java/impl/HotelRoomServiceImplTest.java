package impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ud8.common.exception.ResourceNotFoundException;
import ud8.hotel.domain.entity.HotelRoom;
import ud8.hotel.domain.service.NotificationService;
import ud8.hotel.domain.service.impl.HotelRoomServiceImpl;
import ud8.hotel.persistance.repository.HotelRoomRepository;

import static org.junit.jupiter.api.Assertions.*;
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
    class findByCodeTests {

        @Test
        void givenExistingRoomShouldReturnTheRoom() throws ResourceNotFoundException {
            //Arrange
            HotelRoom roomExpected = new HotelRoom("HR1", 30.0);
            when(repository.findByCode(roomExpected.getCode())).thenReturn(roomExpected);
            //Act
            HotelRoom roomActual = service.findByCode(roomExpected.getCode());
            //Assert
            assertAll(

                    () -> assertEquals(roomExpected, roomActual),
                    () -> verify(repository).findByCode(roomExpected.getCode())



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
        void givenNonExistingRoomShouldReturnFalse_SoNOUpdate() {
            //arrange
            HotelRoom nonExistingRoom = new HotelRoom("HAB001", 30.5);
            when(repository.existsByCode(nonExistingRoom.getCode())).thenReturn(false);//com no exosteix no s'actualitza
            boolean expected = false;
            //act
            boolean actual = service.update(nonExistingRoom);
            //assert
            assertAll(
                    () -> assertEquals(expected, actual),
                    () -> verify(repository, never()).save(nonExistingRoom)

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
}