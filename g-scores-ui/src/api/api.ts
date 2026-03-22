import { API_BASE_URL, API_ENDPOINTS } from "./constant";
import {
  ChartDataResponse,
  ExamGroupResponse,
  ExamResultResponse,
  Top10HighestScoresResponse,
} from "./type";

export const getExamResultBySBD = async (
  sbd: string,
): Promise<ExamResultResponse> => {
  const response = await fetch(
    `${API_BASE_URL}${API_ENDPOINTS.EXAM_RESULT.GET_BY_SBD(sbd)}`,
  );
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message ?? "Failed to fetch exam result");
  }
  return response.json();
};

export const getExamGroups = async (): Promise<ExamGroupResponse[]> => {
  const response = await fetch(
    `${API_BASE_URL}${API_ENDPOINTS.EXAM_GROUP.GET_ALL()}`,
  );
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message ?? "Failed to fetch exam groups");
  }
  return response.json();
};

export const getTop10HighestScores = async (
  group: string,
): Promise<Top10HighestScoresResponse[]> => {
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

export const getChartData = async (): Promise<ChartDataResponse[]> => {
  try {
    const response = await fetch(
      `${API_BASE_URL}${API_ENDPOINTS.EXAM_RESULT.GET_CHART_DATA}`,
    );
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message ?? "Failed to fetch chart data");
    }
    return response.json();
  } catch (error) {
    console.error(error);
    throw new Error("Failed to fetch chart data");
  }
};
