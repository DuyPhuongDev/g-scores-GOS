import { API_BASE_URL, API_ENDPOINTS } from "./constant";
import { ExamResultResponse } from "./type";

export const getExamResultBySBD = async (
  sbd: string,
): Promise<ExamResultResponse> => {
  try {
    const response = await fetch(
      `${API_BASE_URL}${API_ENDPOINTS.EXAM_RESULT.GET_BY_SBD(sbd)}`,
    );
    if (!response.ok) {
      throw new Error("Failed to fetch exam result");
    }
    return response.json();
  } catch (error) {
    console.error(error);
    throw new Error("Failed to fetch exam result");
  }
};

export const getTop10HighestScores = async (
  group: string,
): Promise<ExamResultResponse[]> => {
  try {
    const response = await fetch(
      `${API_BASE_URL}${API_ENDPOINTS.EXAM_RESULT.GET_TOP_10_HIGHEST_SCORES(group)}`,
    );
    if (!response.ok) {
      throw new Error("Failed to fetch top 10 highest scores");
    }
    return response.json();
  } catch (error) {
    console.error(error);
    throw new Error("Failed to fetch top 10 highest scores");
  }
};
