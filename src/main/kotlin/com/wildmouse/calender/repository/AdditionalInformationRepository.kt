package com.wildmouse.calender.repository

import com.wildmouse.calender.entity.AdditionalInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdditionalInformationRepository : JpaRepository<AdditionalInformation, Long>
