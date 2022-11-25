package com.smartmobility.gada_api.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.repository.MemberRepository;
import com.smartmobility.gada_api.store.domain.Store;
import com.smartmobility.gada_api.store.domain.StoreDetails;
import com.smartmobility.gada_api.store.dto.StoreCsvDto;
import com.smartmobility.gada_api.store.repository.StoreRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class CsvReadTest {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    private final Log log = LogFactory.getLog(getClass());

    @Test
    void test_가게정보_입력() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader("/Users/isangmin/Downloads/store_list.csv"));
        List<String[]> csv = csvReader.readAll();

        for(int i=1; i<csv.size(); i++){
            Store store = storeRepository.findByName(csv.get(i)[3]);
            StoreCsvDto storeCsvDto = StoreCsvDto.builder()
                    .localCode(csv.get(i)[1])
                    .name(csv.get(i)[3])
                    .numberAddress(csv.get(i)[4])
                    .streetAddress(csv.get(i)[5])
                    .phone(csv.get(i)[6])
                    .hours(csv.get(i)[7])
                    .businessType(csv.get(i)[8])
                    .lat(csv.get(i)[18])
                    .lon(csv.get(i)[19])
                    .build();
            log.info(storeCsvDto.toString());
            log.info("i = "+i);
            if(store == null){
                Store newStore = modelMapper.map(storeCsvDto, Store.class);
                storeRepository.save(newStore);
            }else{
                modelMapper.map(storeCsvDto, store);
                storeRepository.save(store);
            }
        }
    }

    @Test
    void test_가게제보정보_입력() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader("/Users/isangmin/Downloads/store_list.csv"));
        List<String[]> csv = csvReader.readAll();
        Member member = memberRepository.findById(106L).orElse(new Member());

        for(int i=1; i<csv.size(); i++){
            Store store = storeRepository.findByName(csv.get(i)[3]); // 10 ~ 17
            StoreDetails storeDetails = StoreDetails.builder()
                    .build();
        }
    }

}
