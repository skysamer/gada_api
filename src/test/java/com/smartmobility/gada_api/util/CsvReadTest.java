package com.smartmobility.gada_api.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
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

import java.io.*;
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
        CSVReader csvReader = new CSVReader(new FileReader("/Users/isangmin/Downloads/all_combine_by_road_addr_with_latlon.csv"));
        List<String[]> csv = csvReader.readAll();

        int idx = 1;
        for(int i=1; i<csv.size(); i++){

            Store store = storeRepository.findByName(csv.get(i)[3]);
            StoreCsvDto storeCsvDto = StoreCsvDto.builder()
                    .name(csv.get(i)[0])
                    .streetAddress(csv.get(i)[2])
                    .phone(csv.get(i)[4])
                    .hours(csv.get(i)[3])
                    .businessType(csv.get(i)[1])
                    .lat(csv.get(i)[5])
                    .lon(csv.get(i)[6])
                    .build();
            log.info(storeCsvDto.toString());
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

    @Test
    void test_파일생성(){
        File file = new File("/Users/isangmin/Downloads", "test.csv");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "euc-kr");
                CSVWriter writer = new CSVWriter(osw);
        ) {
            String[] row = {
                    "날짜",
                    "1종",
                    "2종",
                    "3종"
            };
            writer.writeNext(row);
            for(int i=0; i<200; i++){
                int m = (int) (Math.random() * 12 + 1);
                String month = m >= 10 ? String.valueOf(m) : "0" + m;
                int d = (int) (Math.random() * 30 + 1);
                String day = d >= 10 ? String.valueOf(d) : "0" + d;

                String date = 2022 + "/" + month + "/" + day;

                int one = (int) (Math.random() * 70000 + 20000);
                int two = (int) (Math.random() * 70000) + 20000;
                int three = (int) (Math.random() * 70000) + 20000;

                String[] row2 = {date, String.valueOf(one), String.valueOf(two), String.valueOf(three)};
                writer.writeNext(row2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
