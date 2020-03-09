package br.com.ecommerceapi.entity.converter;

import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static ModelMapper modelMapper;

    static {
        Condition<?, ?> isStringBlank = new AbstractCondition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                if(context.getSource() instanceof String) {
                    return null!=context.getSource() && !"".equals(((String) context.getSource()).trim());
                } else {
                    return context.getSource() != null;
                }
            }
        };
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setPropertyCondition(isStringBlank)
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private Mapper() {
    }

    public static <S, D> D map(final S source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

    public static <T, D> List<D> mapAll(final Collection<T> sourceList, Class<D> destination) {
        return sourceList.stream()
                .map(entity -> map(entity, destination))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

}
