package com.twinlions.spkpath.consultant.service;

import com.twinlions.spkpath.consultant.ConsultantDto;
import com.twinlions.spkpath.consultant.ConsultantSearchDto;
import com.twinlions.spkpath.consultant.Specification.ConsultantSpecification;
import com.twinlions.spkpath.consultant.entity.Consultant;
import com.twinlions.spkpath.consultant.repository.ConsultantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepository consultantRepository;

    /**
     * 전체 상담사 리스트를 반환한다.
     *
     * @return List<User> 전체 상담사 리스트
     */
    @Override
    public List<ConsultantDto> listCslt() {
        List<Consultant> consultants = consultantRepository.findAll();
        return consultants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
//        if (consultantRepository.findAll() != null) {
//            return consultantRepository.findAll();
//        }
//        return null;
    }

    /**
     * 상세 조건을 입력 받아 해당 조건을 만족하는 상담사 리스트를 반환한다.
     *
     * @param consultantSearchDto 상세 조건
     * @return List<ConsultantDto> 상세 조건에 부합하는 상담사 리스트
     */
    @Override
    public List<ConsultantDto> listCsltByCond(ConsultantSearchDto consultantSearchDto) {
        Specification<Consultant> spec = (root, query, criteriaBuilder) -> null;

        System.out.println(consultantSearchDto);

        if (consultantSearchDto.getUserName() != "") {
            System.out.println(consultantSearchDto.getUserName());
            spec = spec.and(ConsultantSpecification.equalsName(consultantSearchDto.getUserName()));
        }

        if (consultantSearchDto.getUserSex() != "") {
            spec = spec.and(ConsultantSpecification.equalsSex(consultantSearchDto.getUserSex()));
        }

        if (consultantSearchDto.getCsltExp() != 0) {
            spec = spec.and(ConsultantSpecification.betweenExp(consultantSearchDto.getCsltExp()));
        }

        if (consultantSearchDto.getCsltTag() != null) {
            for (String tag : consultantSearchDto.getCsltTag()) {
                spec = spec.and(ConsultantSpecification.containsTag(tag));
            }
        }

        if (consultantSearchDto.getCsltBoundary() != null) {
            for (String boundary : consultantSearchDto.getCsltBoundary()) {
                System.out.println(boundary);
                spec = spec.and(ConsultantSpecification.containsBoundary(boundary));
            }
        }

        List<Consultant> consultants = consultantRepository.findAll(spec);
        System.out.println(consultants);
        return consultants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 상담사 entity를 dto로 변환한다.
     *
     * @param consultant 상담사 entity
     * @return consultantDto 상담사 dto
     *
     *
     */
    private ConsultantDto convertToDto(Consultant consultant) {
        ConsultantDto consultantDto = new ConsultantDto();
        consultantDto.setUserId(consultant.getUserId());
        consultantDto.setUserEmail(consultant.getUserEmail());
        consultantDto.setUserAge(consultant.getUserAge());
        consultantDto.setUserGrade(consultant.getUserGrade());
        consultantDto.setUserName(consultant.getUserName());
        consultantDto.setUserPhone(consultant.getUserPhone());
        consultantDto.setUserPwd(consultant.getUserPwd());
        consultantDto.setUserSex(consultant.getUserSex());
        consultantDto.setCsltTeam(consultant.getCsltTeam());
        consultantDto.setCsltExp(consultant.getCsltExp());
        consultantDto.setCsltTagFromList(consultant.getCsltTags());
        consultantDto.setCsltBoundaryFromList(consultant.getCsltBoundaries());
        return consultantDto;
    }
}
