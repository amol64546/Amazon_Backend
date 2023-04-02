package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Converter.SellerConverter;
import com.BadaBazaar.BadaBazaar.RequestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.Model.Seller;
import com.BadaBazaar.BadaBazaar.Repository.SellerRepository;
import com.BadaBazaar.BadaBazaar.ResponseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public String addSeller(SellerRequestDto sellerRequestDto) {
        Seller seller = SellerConverter.sellerRequestDtoToSeller(sellerRequestDto);

        sellerRepository.save(seller);
        return "Seller has been added";
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
}
