package com.ruchir.demo.repository.service;

import com.ruchir.demo.constants.Messages;
import com.ruchir.demo.dto.request.OnboardSellerRequestDto;
import com.ruchir.demo.enums.ExceptionCode;
import com.ruchir.demo.exception.EntityNotFoundException;
import com.ruchir.demo.repository.model.Seller;
import com.ruchir.demo.repository.repo.SellerRepository;
import com.ruchir.demo.repository.repo.readonly.SellerReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerRepositoryService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerReadRepository sellerReadRepository;

    public Optional<Seller> findSellerByExistingData(OnboardSellerRequestDto requestDto) {
        return sellerReadRepository.findByAadharNumberOrPanNumberOrContactOrEmailOrGstin(requestDto.getAadharNumber(), requestDto.getPanNumber(), requestDto.getContact(), requestDto.getEmail(), requestDto.getGstin());
    }

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    public Optional<Seller> findSellerById(Integer id) {
        return sellerReadRepository.findById(id);
    }

    public Seller validateAndGetSellerById(Integer id) {
        return findSellerById(id)
                .orElseThrow(()->
                        new EntityNotFoundException(ExceptionCode.R101,
                                String.format(Messages.ENTITY_NOT_FOUND, "Seller", "id", id)));
    }
}
