import { get, post, deletes } from './http'

//----------------------管理员 API
// 管理员登录
export const adminLogin = (data) => post(`admin/login`, data)


//---------------------用户 API
// 返回所有用户
export const getAllUser = () => get(`user/all`)
// 返回指定ID的用户
export const getUserOfId = (id) => get(`user/detail?id=${id}`)
// 添加用户
export const setUser = (data) => post(`user/add`, data)
// 更新用户信息
export const updateUserMsg = (data) => post(`user/update`, data)
// 删除用户
export const deleteUser = (id) => deletes(`user/delete?id=${id}`)


//---------------------收藏列表 API
// 返回的指定用户ID收藏列表
export const getCollectionOfUser = (userId) => get(`collection/detail?userId=${userId}`)
// 删除收藏的歌曲
export const deleteCollection = (userId, songId) => deletes(`/collection/delete?userId=${userId}&&songId=${songId}`)


//---------------------评论列表 API
// 获得指定歌曲ID的评论列表
export const getCommentOfSongId = (songId) => get(`comment/song/detail?songId=${songId}`)
// 更新评论
export const updateCommentMsg = (data) => post(`comment/update`, data)
// 删除评论
export const deleteComment = (id) => deletes(`comment/delete?id=${id}`)


//----------------------歌曲 API
// 添加歌曲
export const setSong = (data) => post(`song/add`, data)
// 返回所有歌曲
export const getAllSong = () => get(`song/all`)
// 返回的指定用户ID收藏列表
export const getSongOfId = (id) => get(`song/detail?id=${id}`)
// 返回指定歌手名的歌曲
export const getSongOfSingerName = (singerName) => get(`song/singerName/detail?singerName=${singerName}`)
// 更新歌曲信息
export const updateSongMsg = (params) => post(`song/update`, params)
// 删除歌曲
export const deleteSong = (id) => deletes(`song/delete?id=${id}`)