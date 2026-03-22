export interface ExamResultResponse {
  sbd: string;
  foreignLanguageCode?: string;
  scores: [Score];
  total?: number;
}

export interface Score {
  id: number;
  name: string;
  score: number;
}

export interface Top10HighestScoresResponse {
  sbd: string;
  groupName: string;
  scores: Score[];
  total: number;
}

export interface ExamGroupResponse {
  id: number;
  groupName: string;
  subjects: SubjectResponse[];
}

export interface SubjectResponse {
  id: number;
  subjectName: string;
}

export interface ChartDataResponse {
  name: string;
  excellent: number;
  good: number;
  average: number;
  weak: number;
}

export interface ErrorResponse {
  status: number;
  message: string;
  timestamp: string;
}

export type GroupCode = "A00" | "A01" | "B" | "C" | "D01" | "D07";

export const GROUP_SUBJECTS: Record<GroupCode, string[]> = {
  A00: ["Toán", "Lý", "Hóa"],
  A01: ["Toán", "Lý", "Anh"],
  B: ["Toán", "Hóa", "Sinh"],
  C: ["Văn", "Sử", "Địa"],
  D01: ["Toán", "Văn", "Anh"],
  D07: ["Toán", "Hóa", "Anh"],
};

export interface ChartDataResponse {
  subjectId: number;
  subjectName: string;
  excellent: number;
  good: number;
  average: number;
  weak: number;
  totalStudents: number;
}
