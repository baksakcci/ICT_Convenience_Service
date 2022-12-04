package com.example.sinabro.service;

import com.example.sinabro.dto.item.ItemNameResponseDto;
import com.example.sinabro.dto.item.ItemNameResponseInterface;
import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.dto.item.ItemResponseDto;
import com.example.sinabro.dto.rental.RentalsRequestDto;
import com.example.sinabro.dto.rental.RentalsResponseDto;
import com.example.sinabro.dto.user.UserAdminRequestDto;
import com.example.sinabro.dto.user.UserAdminResponseDto;
import com.example.sinabro.dto.user.UsersLoginRequestDto;
import com.example.sinabro.dto.user.UsersLoginResponseDto;
import com.example.sinabro.entity.item.IsRental;
import com.example.sinabro.entity.item.Item;
import com.example.sinabro.entity.user.Users;
import com.example.sinabro.entity.Rental.Rentals;
import com.example.sinabro.entity.user.UsersRole;
import com.example.sinabro.exception.*;
import com.example.sinabro.repository.ItemRepository;
import com.example.sinabro.repository.RentalsRepository;
import com.example.sinabro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UsersRepository usersRepository;
    private final RentalsRepository rentalsRepository;
    private final ItemRepository itemRepository;

    /*
    [[ USER ]]
     */
    @Transactional
    public UserAdminResponseDto createUser(UserAdminRequestDto urd) {
        if (!usersRepository.findByStudentId(urd.getStudentId()).isEmpty()) {
            throw new SignupViolationException();
        }

        Users users = new Users(urd.getStudentId(), urd.getName(), urd.getPassword());
        if ("admin".equals(urd.getStudentId())) {
            users.setUsersRole(UsersRole.ADMIN);
        } else {
            users.setUsersRole(UsersRole.USER);
        }
        usersRepository.save(users);

        return UserAdminResponseDto.toDto(users);
    }
    @Transactional(readOnly = true)
    public UserAdminResponseDto findUser(String id) {
        Users users = usersRepository.findByStudentId(id).orElseThrow(UserNotFoundException::new);
        return UserAdminResponseDto.toDto(users);
    }
    @Transactional(readOnly = true)
    public List<UserAdminResponseDto> findMemberAll() {
        List<Users> items = usersRepository.findAll();
        List<UserAdminResponseDto> result = new ArrayList<>();
        for (Users users : items) {
            result.add(UserAdminResponseDto.toDto(users));
        }
        return result;
    }
    @Transactional
    public void deleteMember(String name) {
        Users users = usersRepository.findByName(name).orElseThrow(UserNotFoundException::new);
        usersRepository.delete(users);
    }



    /*
    [[ ITEM ]]
     */
    @Transactional
    public List<ItemResponseDto> findItemAll() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemResponseDto> dtoList = new ArrayList<>();
        for(Item i : itemList) {
            ItemResponseDto toDto = ItemResponseDto.toDto(i);
            dtoList.add(toDto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<ItemNameResponseInterface> findItemNameAll() {
        List<ItemNameResponseInterface> groupByItemNameWithJPQL = itemRepository.findGroupByItemNameWithJPQL();
        return groupByItemNameWithJPQL;
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findItemDetailAll(String itemName) {
        List<Item> items = itemRepository.findByItemNameContaining(itemName);
        ArrayList<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item i : items) {
            ItemResponseDto itemResponseDto = ItemResponseDto.toDto(i);
            itemResponseDtos.add(itemResponseDto);
        }
        return itemResponseDtos;
    }

    @Transactional
    public void createItem(ItemRequestDto ite) {
        Item item1 = itemRepository.findByItemDetailName(ite.getItemDetailName()).get();
        if(item1.getItemDetailName().equals(ite.getItemDetailName())) {
            throw new ItemDontCreateException();
        }
        if(item1.getItemName().equals(ite.getItemName())) {
            throw new ItemDontCreateException();
        }

        Item item = new Item(ite.getItemName(), ite.getItemDetailName(), ite.getIsRental());
        itemRepository.save(item);
    }
    @Transactional
    public void deleteItem(String itemDetailName) {
        Item item = itemRepository.findByItemDetailName(itemDetailName).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }


    /*
    [[ Item RENTAL ]]
     */
    @Transactional
    public void createRental(RentalsRequestDto rrd) {
        // 어떤 회원에게 알림을 넣을건지, 어떤 물품을 대여했는지
        Users rentalUsers = usersRepository.findByStudentId(rrd.getStudentId()).orElseThrow(UserNotFoundException::new);
        Item rentalItem = itemRepository.findByItemDetailName(rrd.getItemDetailName()).orElseThrow(ItemNotFoundException::new);

        // 물품이 대여상태가 아닐 경우 에러
        if(!(rentalItem.getIsRental().equals(IsRental.CAN))) {
            throw new ItemCanNotUseException();
        }

        Rentals rentals = new Rentals(rrd.getContent(), rentalUsers, rentalItem);
        rentalsRepository.saveAndFlush(rentals);

        // 물품 대여 상태로 바꾸기
        rentalItem.setIsRental(IsRental.CANT);
        itemRepository.save(rentalItem);
    }

    @Transactional
    public void returnRental(RentalsRequestDto rrd) {
        Users rentalUsers = usersRepository.findByStudentId(rrd.getStudentId()).orElseThrow(UserNotFoundException::new);
        Item rentalItem = itemRepository.findByItemDetailName(rrd.getItemDetailName()).orElseThrow(ItemNotFoundException::new);

        // 물품이 대여가능 상태일 경우 반납 불가
        if(rentalItem.getIsRental().equals(IsRental.CAN)) {
            throw new ItemCanNotUseException();
        }
        Rentals rentals = new Rentals(rrd.getContent(), rentalUsers, rentalItem);
        rentalsRepository.saveAndFlush(rentals);

        // 반납도 반납 내역 따로 만들고 로직 짜야될듯듯
        rentalItem.setIsRental(IsRental.CAN);
        itemRepository.save(rentalItem);

        /*
        // 제일 최근 날짜의 랜탈에서 아이템 객체를 가져와서 세이브
        // 학번만 가지고 할 수도 있지만, 만약 같은 물품을 다시 빌린다면 2개가 되어서 이렇게 하는것이 좋다고 생각
        // 물품 대여는 1개만 가능하므로 학번과 IsRental을 검색해서 GROUP_BY로 묶어서 조회할 수도 있음.
        Rentals rental = rentalsRepository.findTopByUsers_StudentIdOrderByBorrowDateDesc(rentalUsers.getStudentId());
        Item item = rental.getItem();
        item.setIsRental(IsRental.CAN);
        */
    }

    @Transactional(readOnly = true)
    public List<RentalsResponseDto> findAll() {
        List<Rentals> rentals = rentalsRepository.findAll();
        List<RentalsResponseDto> rentalsResponseDtoList = new ArrayList<>();
        for(Rentals n : rentals) {
            RentalsResponseDto rentalsResponseDto = RentalsResponseDto.toDto(n);
            rentalsResponseDtoList.add(rentalsResponseDto);
        }
        return rentalsResponseDtoList;
    }
    @Transactional(readOnly = true)
    public RentalsResponseDto findRental(Long id) {
        Rentals rentals = rentalsRepository.findById(id).orElseThrow();
        RentalsResponseDto rentalsResponseDto = RentalsResponseDto.toDto(rentals);
        return rentalsResponseDto;
    }
    @Transactional(readOnly = true)
    public List<RentalsResponseDto> searchRental(String keyword) {
        List<Rentals> rentals = rentalsRepository.findByContentsContaining(keyword);
        List<RentalsResponseDto> rentalsResponseDtoList = new ArrayList<>();
        for(Rentals n : rentals) {
            RentalsResponseDto rentalsResponseDto = RentalsResponseDto.toDto(n);
            rentalsResponseDtoList.add(rentalsResponseDto);
        }
        return rentalsResponseDtoList;
    }

    /*
    @Transactional
    public void deleteRental(RentalRequestDto rrd, Long rentalId) {
        // 어떤 회원의 알림을 삭제할 건지
        User rentalUser = userRepository.findByStudentId(rrd.getStudentId()).orElseThrow(UserNotFoundException::new);

        rentalRepository.findById(rentalId).orElseThrow(NotionNotFoundException::new);
        rentalRepository.deleteById(rentalId);
    }

     */
    /* 일단 수정은 없는걸로
    @Transactional
    public void updateNotice(NoticeRequestDto nrd, Long noticeId) {
        // 어떤 회원에게 알림을 넣을건지
        User noticeUser = userRepository.findByStudentId(nrd.getStudentId()).orElseThrow(UserNotFoundException::new);

        // DB 있는지 없는지 검사
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(NotionNotFoundException::new);
        Notice editNotice = notice.editNotice(nrd.getTitle(), nrd.getContent());
        noticeRepository.save(editNotice);
    }
     */


}
