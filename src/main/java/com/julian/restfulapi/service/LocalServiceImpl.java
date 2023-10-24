package com.julian.restfulapi.service;

import com.julian.restfulapi.entity.Local;
import com.julian.restfulapi.error.local.LocalNotFoundException;
import com.julian.restfulapi.error.local.LocalSaveException;
import com.julian.restfulapi.repository.LocalRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService{

    private final LocalRepository localRepository;
    private final MessageSource messageSource;

    @Autowired
    public LocalServiceImpl(LocalRepository localRepository, MessageSource messageSource) {
        this.localRepository = localRepository;
        this.messageSource = messageSource;
    }


    @Override
    public List<Local> findAllLocals() {
        return localRepository.findAll();
    }

    @Override
    public Local saveLocal(Local local) {

            return localRepository.save(local);

    }

    @Override
    public Local updateLocal(Long id, Local local) {
        Local localDb = localRepository.findById(id).get();
        if(Objects.nonNull(local.getFloor()) && !"".equalsIgnoreCase(local.getFloor())){
            localDb.setFloor(local.getFloor());
        }
        if(Objects.nonNull(local.getName()) && !"".equalsIgnoreCase(local.getName())){
            localDb.setName(local.getName());
        }
        return localRepository.save(localDb);
    }

    @Override
    public void deleteLocal(Long id) {
        localRepository.deleteById(id);
    }

    @Override
    public Optional<Local> findLocalByNameWithJPQL(String name) {
        return localRepository.findLocalByNameWithJPQL(name);
    }

    @Override
    public Optional<Local> findByName(String name){
        return localRepository.findByName(name);
    }

    @Override
    public Optional<Local> findByNameIgnoreCase(String name) {
        return localRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Local findLocalById(Long id) {
        Optional<Local> localOptional = localRepository.findById(id);
        return localOptional.orElseThrow(() -> new LocalNotFoundException(
                messageSource.getMessage("error.entity.notfound", new Object[]{id}, Locale.getDefault())
        ));
    }


}
