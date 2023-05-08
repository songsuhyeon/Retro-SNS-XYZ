import { axiosInstance } from "./instance";

const FRIEND = "/friend";

// 친구 요청
export const postFollow = (userSeq: string) => {
  return axiosInstance.post(FRIEND, userSeq);
};

// 친구 요청 취소
export const putCancelFollow = (userSeq: string) => {
  return axiosInstance.put(FRIEND, userSeq);
};

// 친구 요청 수락
export const putAcceptFollow = (userSeq: string) => {
  return axiosInstance.put(FRIEND, userSeq);
};

// 친구 끊기
export const deleteFollow = (userSeq: string) => {
  return axiosInstance.put(`${FRIEND}/${userSeq}`);
};

// 사용자 차단
export const postBlock = (userSeq: string) => {
  return axiosInstance.put(`${FRIEND}/block`, userSeq);
};

// 사용자 차단 해제
export const deleteBlock = (userSeq: string) => {
  return axiosInstance.put(`${FRIEND}/block/${userSeq}`);
};



