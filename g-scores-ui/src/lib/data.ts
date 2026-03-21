export type GroupCode = "A00" | "A01" | "B" | "C" | "D01" | "D07";

export interface Candidate {
  id: string;
  sbd: string;
  name: string;
  scores: {
    math: number;
    physics: number;
    chemistry: number;
    biology: number;
    literature: number;
    history: number;
    geography: number;
    civics: number;
    english: number;
  };
  group: GroupCode;
}

export const GROUP_SUBJECTS: Record<GroupCode, (keyof Candidate["scores"])[]> =
  {
    A00: ["math", "physics", "chemistry"],
    A01: ["math", "physics", "english"],
    B: ["math", "chemistry", "biology"],
    C: ["literature", "history", "geography"],
    D01: ["math", "literature", "english"],
    D07: ["math", "chemistry", "english"],
  };

export const MOCK_CANDIDATES: Candidate[] = [
  {
    id: "1",
    sbd: "24001284",
    name: "Nguyễn Lâm Trường",
    scores: {
      math: 9.8,
      physics: 9.6,
      chemistry: 9.7,
      biology: 8.0,
      literature: 7.5,
      history: 6.0,
      geography: 6.5,
      civics: 8.5,
      english: 9.0,
    },
    group: "A00",
  },
  {
    id: "2",
    sbd: "24003912",
    name: "Phạm Văn Tuấn",
    scores: {
      math: 9.6,
      physics: 9.8,
      chemistry: 9.5,
      biology: 7.5,
      literature: 8.0,
      history: 7.0,
      geography: 7.5,
      civics: 9.0,
      english: 8.5,
    },
    group: "A00",
  },
  {
    id: "3",
    sbd: "24002201",
    name: "Lê Hoàng Nam",
    scores: {
      math: 9.4,
      physics: 9.4,
      chemistry: 9.8,
      biology: 8.5,
      literature: 7.0,
      history: 8.0,
      geography: 8.0,
      civics: 8.0,
      english: 7.5,
    },
    group: "A00",
  },
  {
    id: "4",
    sbd: "24000852",
    name: "Trần Huy Hoàng",
    scores: {
      math: 9.2,
      physics: 9.5,
      chemistry: 9.5,
      biology: 9.0,
      literature: 6.5,
      history: 7.5,
      geography: 7.0,
      civics: 9.5,
      english: 8.0,
    },
    group: "A00",
  },
  {
    id: "5",
    sbd: "24009110",
    name: "Võ Nhật Anh",
    scores: {
      math: 9.0,
      physics: 9.5,
      chemistry: 9.6,
      biology: 8.0,
      literature: 8.5,
      history: 6.5,
      geography: 8.5,
      civics: 9.0,
      english: 9.5,
    },
    group: "A00",
  },
  {
    id: "6",
    sbd: "24005566",
    name: "Nguyễn Minh Anh",
    scores: {
      math: 9.2,
      physics: 8.75,
      chemistry: 9.0,
      biology: 7.5,
      literature: 8.5,
      history: 8.25,
      geography: 8.75,
      civics: 9.5,
      english: 9.8,
    },
    group: "A00",
  },
];

export const SUBJECT_LABELS: Record<string, string> = {
  math: "Toán",
  physics: "Lý",
  chemistry: "Hóa",
  biology: "Sinh",
  literature: "Văn",
  history: "Sử",
  geography: "Địa",
  civics: "GDCD",
  english: "Ngoại ngữ",
};
