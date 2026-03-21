export const API_BASE_URL =
  process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8888/api/v1";

export const API_ENDPOINTS = {
  EXAM_RESULT: {
    GET_BY_SBD: (sbd: string) => `/exam-results/${sbd}`,
    GET_TOP_10_HIGHEST_SCORES: (group: string) =>
      `/exam-results/top-10-highest-scores?group=${group}`,
  },
} as const;
