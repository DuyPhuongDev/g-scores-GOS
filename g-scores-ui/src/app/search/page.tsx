"use client";

import React, { useState } from "react";
import {
  Sidebar,
  TopBar,
  MobileNav,
} from "@/components/layout/layout-components";
import { Search } from "lucide-react";
import { getExamResultBySBD } from "@/api/api";
import { ExamResultResponse } from "@/api/type";

export default function SearchPage() {
  const [searchQuery, setSearchQuery] = useState("");
  const [result, setResult] = useState<ExamResultResponse>();
  const [hasSearched, setHasSearched] = useState(false);

  const handleSearch = async (e: React.FormEvent) => {
    e.preventDefault();
    const examResult = await getExamResultBySBD(searchQuery);
    setResult(examResult);
    setHasSearched(true);
  };

  return (
    <div className="min-h-screen bg-surface">
      <TopBar />
      <Sidebar />

      <main className="lg:ml-64 pt-24 px-6 pb-24 lg:pb-12 min-h-screen">
        <div className="max-w-5xl mx-auto">
          {/* Search Section */}
          <section className="mb-12">
            <div className="mb-8">
              <h1 className="text-3xl font-extrabold tracking-tight text-blue-900 mb-2">
                Check exam result
              </h1>
              <p className="text-slate-500 font-medium">
                Enter the student ID to view the detailed exam result.
              </p>
            </div>

            <div className="bg-white rounded-xl p-8 shadow-xl shadow-blue-900/5 border border-slate-200 max-w-2xl">
              <form
                onSubmit={handleSearch}
                className="flex flex-col md:flex-row gap-4"
              >
                <div className="flex-1 relative">
                  <input
                    id="sbd"
                    type="text"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    placeholder="Ví dụ: 24001284"
                    className="w-full bg-slate-50 border-b-2 border-slate-200 px-4 py-4 rounded-lg outline-none transition-all text-slate-900 font-semibold tracking-widest"
                  />
                </div>
                <button
                  type="submit"
                  className="bg-blue-900 text-white px-8 py-4 rounded-lg font-bold flex items-center justify-center gap-2 hover:bg-blue-800 transition-all active:scale-95"
                >
                  <Search className="w-5 h-5" /> Check result
                </button>
              </form>
            </div>
          </section>

          {/* Results Section */}
          {hasSearched && result ? (
            <div className="bg-white rounded-xl border border-slate-200 shadow-xl shadow-blue-900/5 overflow-hidden">
              {/* Header */}
              <div className="bg-blue-900 px-8 py-5 flex items-center justify-between relative overflow-hidden">
                <div className="absolute top-0 right-0 w-40 h-40 bg-white/5 rounded-full -mr-20 -mt-20 blur-3xl" />
                <div>
                  <span className="text-[10px] font-bold tracking-[0.1em] uppercase text-blue-300 mb-1 block">
                    Student information
                  </span>
                  <p className="text-white font-bold text-lg tracking-widest">
                    SBD: {result.sbd}
                  </p>
                </div>
              </div>

              {/* Score Grid */}
              <div className="grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-5 divide-x divide-y divide-slate-100">
                {[
                  { label: "Toán", score: result.math },
                  { label: "Ngữ văn", score: result.literature },
                  { label: "Ngoại ngữ", score: result.foreignLanguage },
                  { label: "Vật lý", score: result.physics },
                  { label: "Hóa học", score: result.chemistry },
                  { label: "Sinh học", score: result.biology },
                  { label: "Lịch sử", score: result.history },
                  { label: "Địa lý", score: result.geography },
                  { label: "GDCD", score: result.civicEducation },
                  {
                    label: "Mã ngoại ngữ",
                    score: result.foreignLanguageCode,
                  },
                ].map(({ label, score }) => (
                  <div
                    key={label}
                    className="flex flex-col items-center justify-center gap-1 px-4 py-6 hover:bg-slate-50 transition-colors"
                  >
                    <span className="text-[11px] font-bold tracking-[0.05em] uppercase text-slate-400">
                      {label}
                    </span>
                    <span className="text-2xl font-bold">
                      {score !== null && score !== undefined ? score : "—"}
                    </span>
                  </div>
                ))}
              </div>
            </div>
          ) : hasSearched ? (
            <p className="text-slate-500 font-bold text-center py-20">
              No student found with this SBD.
            </p>
          ) : null}
        </div>
      </main>

      <MobileNav />
    </div>
  );
}
