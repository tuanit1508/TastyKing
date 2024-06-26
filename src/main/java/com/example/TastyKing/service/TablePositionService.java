package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.TablePositionRequest;
import com.example.TastyKing.dto.request.UpdateTablePosition;
import com.example.TastyKing.dto.response.TablePositionResponse;
import com.example.TastyKing.entity.TablePosition;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.TablePositionMapper;
import com.example.TastyKing.repository.TablePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablePositionService {

    @Autowired
    private TablePositionMapper tablePositionMapper;
    @Autowired
    private TablePositionRepository tablePositionRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public TablePositionResponse createTablePosition(TablePositionRequest request){
        if(tablePositionRepository.existsByTablePosition(request.getTablePosition()))
            throw new AppException(ErrorCode.POSITION_EXISTED);
        TablePosition tablePosition = tablePositionMapper.toTablePosition(request);
        tablePosition = tablePositionRepository.save(tablePosition);
        return tablePositionMapper.toTablePositionResponse(tablePosition);

    }

    public List<TablePositionResponse> getAllPosition(){
        var categorys =tablePositionRepository.findAll();

        return categorys.stream().map(tablePositionMapper::toTablePositionResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TablePositionResponse updatePosition(Long tablePositionID, UpdateTablePosition request){
        TablePosition tablePosition = tablePositionRepository.findById(tablePositionID).orElseThrow(() ->
                new AppException(ErrorCode.POSITION_NOT_EXIST));

        tablePosition.setTablePosition(request.getTablePosition());
        tablePosition.setTableQuantity(request.getTableQuantity());
        TablePosition updatedPosition =tablePositionRepository.save(tablePosition);
        return tablePositionMapper.toTablePositionResponse(updatedPosition);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deletePosition(Long tablePositionID) {
        tablePositionRepository.deleteById(tablePositionID);
        return "Deleted successfull";
    }
}
