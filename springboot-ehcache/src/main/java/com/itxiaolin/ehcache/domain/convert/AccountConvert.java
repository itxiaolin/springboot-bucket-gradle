package com.itxiaolin.ehcache.domain.convert;

import com.itxiaolin.ehcache.domain.dto.AccountDTO;
import com.itxiaolin.ehcache.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConvert {

    AccountConvert INSTANCE = Mappers.getMapper(AccountConvert.class);

    AccountDTO entity2dto(Account entity);

    Account dto2entity(AccountDTO dto);

}
