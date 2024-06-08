package com.BadaBazaar.BadaBazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.converter.SellerConverter;
import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.model.Seller;
import com.BadaBazaar.BadaBazaar.repository.SellerRepository;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SellerServiceImp implements SellerService {

    private final SellerRepository sellerRepository;

    private final ModelMapper modelMapper;

    @Override
    @CachePut(value = "seller", key = "#sellerId")
    public Object addSeller(SellerRequestDto sellerRequestDto) {
//        Seller seller = SellerConverter.sellerRequestDtoToSeller(sellerRequestDto);

        Seller seller = modelMapper.map(sellerRequestDto, Seller.class);

        sellerRepository.save(seller);
        return seller;
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();

        List<SellerResponseDto> list = new ArrayList<>();

        for(Seller s: sellers){
            list.add(SellerConverter.sellerToSellerResponseDto(s));
        }
        return list;
    }

    public SellerResponseDto getSellerByPan(String panNo) {
        Seller seller = sellerRepository.findByPanNo(panNo);
        return SellerConverter.sellerToSellerResponseDto(seller);
    }

    @Override
    public SellerResponseDto getSellerById(int sellerId)
    {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        return seller.map(SellerConverter::sellerToSellerResponseDto).orElse(null);
    }



}
