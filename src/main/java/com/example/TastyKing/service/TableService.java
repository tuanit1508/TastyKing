package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.TableRequest;
import com.example.TastyKing.dto.request.UpdateTableRequest;
import com.example.TastyKing.dto.request.UpdateTableStatusRequest;
import com.example.TastyKing.dto.response.TableResponse;
import com.example.TastyKing.entity.TablePosition;
import com.example.TastyKing.entity.Tables;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.TableMapper;
import com.example.TastyKing.repository.TablePositionRepository;
import com.example.TastyKing.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TablePositionRepository tablePositionRepository;


    public TableResponse createNewTable(TableRequest tableRequest){
        Tables tables = tableMapper.toTable(tableRequest);
        TablePosition tablePosition = tablePositionRepository.findById(tableRequest.getTablePosition().getTablePositionID())
                .orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_EXIST));
        tables.setTablePosition(tablePosition);
        tables.setTableStatus("Available");
        Tables saveTables = tableRepository.save(tables);
        return tableMapper.toTableResponse(saveTables);

    }


    public List<TableResponse> getAllTable(){
        List<Tables> tables = tableRepository.findAll();
        return tables.stream()
                .map(tableMapper::toTableResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TableResponse updateTable(Long tableID,UpdateTableRequest request){
        Tables tables =tableRepository.findById(tableID).orElseThrow(() ->
                new AppException(ErrorCode.TABLE_NOT_EXIST));
        TablePosition tablePosition = tablePositionRepository.findById(request.getTablePosition().getTablePositionID())
                .orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_EXIST));
        tables.setTablePosition(tablePosition);
        tables.setTableName(request.getTableName());
        tables.setNumOfchair(request.getNumOfchair());
        Tables updateTable = tableRepository.save(tables);
        return tableMapper.toTableResponse(updateTable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TableResponse updateTableStatus(Long tableID, UpdateTableStatusRequest request) {
        Tables table = tableRepository.findById(tableID).orElseThrow(() ->
                new AppException(ErrorCode.TABLE_NOT_EXIST));
        table.setTableStatus(request.getTableStatus());
        Tables updatedTable = tableRepository.save(table);
        return tableMapper.toTableResponse(updatedTable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTable(Long tableID) {
        tableRepository.deleteById(tableID);
        return "Delete table successfull";

    }
    public List<TableResponse> getTableByPosition(Long tablePositionID) {
        List<Tables> tables = tableRepository.findByTablePosition_TablePositionID(tablePositionID);
        return tables.stream()
                .map(tableMapper::toTableResponse)
                .collect(Collectors.toList());
    }
}



