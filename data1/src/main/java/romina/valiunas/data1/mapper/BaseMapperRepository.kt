package romina.valiunas.data1.mapper

interface BaseMapperRepository <E, D> {

    fun transform(type: E): D

    fun transformToRepository(type: D): E

}