import axios from 'axios'
import { get, post } from './http'


// ==================== 用户 API====================
// 登录
export const loginIn = (data) => post(`user/login`, data)
// 注册
export const SignUp = (data) => post(`user/add`, data)
// 更新用户信息
export const updateUserMsg = (data) => post(`user/update`, data)
// 返回指定ID的用户
export const getUserOfId = (id) => get(`user/detail?id=${id}`)


// ==================== 歌曲 API====================
// 获取全部歌曲
export const getAllSong = () => get('song/all')
// 搜索歌曲
export const selectByKeyword = (keyword) => get(`song/search?keyword=${keyword}`)
// 返回指定歌曲ID的歌曲
export const getSongOfId = (id) => get(`song/detail?id=${id}`)



// ===================== 收藏 API====================
// 返回的指定用户ID的收藏列表
export const getCollectionOfUser = (userId) => get(`song/collect?userId=${userId}`)
// 添加收藏的歌曲
export const setCollection = (data) => post(`collection/add`, data)


// ===================== 评论 API======================
// 添加评论
export const setComment = (data) => post(`comment/add`, data)
// 返回所有评论
export const getAllComment = (songId) => get(`comment/song/detail?songId=${songId}`)

