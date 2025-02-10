package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.DispatcherProvider
import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository interface for accessing photo list data.
 */
class PhotoListRepositoryImpl  @Inject constructor(
    private val remoteDataSource: PhotoListRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PhotoListRepository {

    /**
     * Retrieves list of photos.
     * @return [Flow] of [PicsumResult] wrapped around [List] of [PhotoListItem] objects representing photos.
     */
    override suspend fun getPhotoList(): Flow<PicsumResult<List<PhotoListItem>>> =
        flow {
            when (val result = remoteDataSource.getPhotoList()) {
                is PicsumResult.Success -> emit(
                    PicsumResult.Success(result.invoke().map { it.toDomainModel() })
                )

                is PicsumResult.Fail -> emit(PicsumResult.Fail(result.throwable))
                else -> {}
            }
        }.flowOn(dispatcherProvider.ioDispatcher)
}