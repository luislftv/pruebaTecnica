package com.example.pruebatecnica.repository;

import com.example.pruebatecnica.model.Match;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match, String> {

    List<Match> findByTournamentId(String tournamentId);

    @Aggregation(pipeline =      {
            "{ '$match': { 'tournamentId': ?0 } }",
            "{ '$facet': { 'homeStats': [{ '$group': { '_id': '$homeTeam.teamId', 'matchesPlayed': { '$sum': 1 }, 'goalsScored': { '$sum': '$homeTeam.score' } } }], 'awayStats': [{ '$group': { '_id': '$awayTeam.teamId', 'matchesPlayed': { '$sum': 1 }, 'goalsScored': { '$sum': '$awayTeam.score' } } }] } }",
            "{ '$project': { 'teams': { '$concatArrays': ['$homeStats', '$awayStats'] } } }",
            "{ '$unwind': '$teams' }",
            "{ '$group': { '_id': '$teams._id', 'matchesPlayed': { '$sum': '$teams.matchesPlayed' }, 'goalsScored': { '$sum': '$teams.goalsScored' } } }",
            "{ '$lookup': { 'from': 'tournaments', 'let': { 'teamId': '$_id' }, 'pipeline': [{ '$addFields': { 'tournamentIdStr': { '$toString': '$_id' } } }, { '$match': { '$expr': { '$eq': ['$tournamentIdStr', ?0] } } }, { '$unwind': '$teams' }, { '$match': { '$expr': { '$eq': ['$teams._id', '$$teamId'] } } }, { '$project': { 'teamName': '$teams.name' } }], 'as': 'teamData' } }",
            "{ '$unwind': { 'path': '$teamData', 'preserveNullAndEmptyArrays': true } }",
            "{ '$project': { 'teamId': '$_id', 'teamName': { '$ifNull': ['$teamData.teamName', 'Unknown'] }, 'matchesPlayed': 1, 'goalsScored': 1 } }"})
    List<TeamStatsProjection> getAllTeamsStats(String tournamentId);

    interface TeamStatsProjection {
        String getTeamId();
        String getTeamName();
        int getMatchesPlayed();
        int getGoalsScored();
    }
}