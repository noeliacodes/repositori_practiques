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
    class DeleteTests {
        @Test
        void givenNonExistingCode_NOUpdate_returnFalse() {
            //arrange
            HotelRoom nonExistingRoom = new HotelRoom("HAB1313");
            when(repository.existsByCode(nonExistingRoom.getCode())).thenReturn(false);
            boolean expected = false;
            //act
            boolean actual = service.delete(nonExistingRoom.getCode());
            //assert
            assertAll(
                    () -> assertEquals(expected, actual),
                    () -> verify(repository, never()).delete(nonExistingRoom.getCode())

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