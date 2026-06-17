package com.curbandside.app.transformers;

import com.curbandside.app.DTO.GeoJson.LatitudeLongitudeListDto;
import org.hibernate.query.ResultListTransformer;
import org.hibernate.query.TupleTransformer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class LatitudeLongitudeListTransformer implements TupleTransformer<LatitudeLongitudeListDto>, ResultListTransformer<LatitudeLongitudeListDto> {

    final LatitudeLongitudeListDto latitudeLongitudeListDto = new LatitudeLongitudeListDto();

    @Override
    public LatitudeLongitudeListDto transformTuple(Object[] tuple, String[] strings) {

        latitudeLongitudeListDto.addLatitude((BigDecimal) tuple[0]);
        latitudeLongitudeListDto.addlongitude((BigDecimal) tuple[1]);
        return latitudeLongitudeListDto;
    }

    @Override
    public List<LatitudeLongitudeListDto> transformList(List<LatitudeLongitudeListDto> list) {
        return Collections.singletonList(latitudeLongitudeListDto);
    }
}
